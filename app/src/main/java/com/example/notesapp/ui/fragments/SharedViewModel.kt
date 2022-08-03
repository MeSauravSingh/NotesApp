package com.example.notesapp.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.Data
import com.example.notesapp.repository.Repository
import com.example.notesapp.room.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(application: Application):AndroidViewModel(application){

    private val notesDao = NotesDatabase.getDatabase(application).notesDao()
    private val repository: Repository = Repository(notesDao)

    fun insert(
        data: Data
    ) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(data)
    }

    fun delete(
        data: Data
    ) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(data)
    }

    fun update(
        data: Data
    ) = viewModelScope.launch(Dispatchers.IO){
        repository.update(data)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun searchQuery(searchQuery: String):LiveData<List<Data>>{
       return  repository.searchDatabase(searchQuery)
    }

    val getAllNotes:LiveData<List<Data>> = repository.getAllNotes

}