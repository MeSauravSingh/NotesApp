package com.example.notesapp.ui.fragments.editNote

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.Data
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentEditNoteBinding
import com.example.notesapp.ui.fragments.SharedViewModel
import kotlin.math.log

class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {

    private lateinit var  binding: FragmentEditNoteBinding
    private val viewModel: SharedViewModel by viewModels()
    private val args: EditNoteFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentEditNoteBinding.bind(view)

        val data =  args.data

        binding.apply {

            etTitle.setText(data.title)
            etDesc.setText(data.desc)
            btnEditNote.setOnClickListener {
                val title = etTitle.text.toString().trim()
                val desc = etDesc.text.toString().trim()
                val updatedData = Data(data.id,title,desc)

                when{
                    title.isEmpty()-> stTitle.error = "Title is required"
                    else->{
                        viewModel.update(updatedData)
                        Toast.makeText(requireContext(), "Note Edited Successfully", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().popBackStack()
                    }
                }
            }
        }

    }



}