package com.example.notesapp.ui.fragments.addNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.Data
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentAddNoteBinding
import com.example.notesapp.ui.fragments.SharedViewModel


class AddNoteFragment : Fragment(R.layout.fragment_add_note) {

    private val viewModel: SharedViewModel by viewModels()
    private lateinit var  binding: FragmentAddNoteBinding




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddNoteBinding.bind(view)

        binding.apply {
            btnAddNote.setOnClickListener {
                val title = etTitle.text.toString().trim()
                val desc = etDesc.text.toString().trim()
                when{
                    title.isEmpty() -> stTitle.error = "Title is required"
                    else->{
                        viewModel.insert(
                            Data(0, title, desc)
                        )
                        Toast.makeText(requireContext(), "Note Added Successful", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()

                    }

                }


            }
        }



    }



}
