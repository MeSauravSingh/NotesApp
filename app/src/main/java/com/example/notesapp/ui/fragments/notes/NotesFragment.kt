package com.example.notesapp.ui.fragments.notes

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.asFlow
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.Data
import com.example.notesapp.R
import com.example.notesapp.adapter.Adapter
import com.example.notesapp.databinding.FragmentAddNoteBinding
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.observeOnce
import com.example.notesapp.ui.fragments.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest

class NotesFragment : Fragment(R.layout.fragment_notes), Adapter.OnItemClickListener
    ,SearchView.OnQueryTextListener {

    private lateinit var  binding: FragmentNotesBinding
    private val viewModel : SharedViewModel by viewModels()
    private val myAdapter = Adapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNotesBinding.bind(view)

        binding.recyclerView.apply {
            adapter = myAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        binding.apply {

            viewModel.getAllNotes.observe(viewLifecycleOwner){
                myAdapter.submitList(it)
                if (it.isEmpty()){
                    tvNoData.visibility = View.VISIBLE
                    ivNodata.visibility = View.VISIBLE
                }else{
                    tvNoData.visibility = View.INVISIBLE
                    ivNodata.visibility = View.INVISIBLE
                }
            }

            btnFab.setOnClickListener {
                findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToAddFragment())
            }

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val myData = myAdapter.currentList[position]

                    viewModel.delete(myData)
                    myAdapter.notifyItemInserted(position)
                    myAdapter.notifyItemRemoved(position)

                    Snackbar.make(binding.recyclerView, "Deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo") {
                            viewModel.insert(myData)

                        }.show()

                }
            }).attachToRecyclerView(recyclerView)


        }
        setHasOptionsMenu(true)




    }


    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all -> {
                viewModel.deleteAll()
                Toast.makeText(requireContext(), "All Notes Deleted", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onItemClickListener(myData: Data, position: Int) {
        findNavController().navigate(
            NotesFragmentDirections.actionNotesFragmentToEditFragment(myData)
        )
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val searchQuery = "%$query%"
        searchDatabase(searchQuery)
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        val searchQuery = "%$query%"
        searchDatabase(searchQuery)
        return true
    }

    private fun searchDatabase(query: String?){
        if (query != null ) {
            viewModel.searchQuery(query).observe(viewLifecycleOwner) { list ->
                list?.let {
                    myAdapter.submitList(it)
                }
            }
        }

    }





}