package com.example.notesapp.repository

import androidx.lifecycle.LiveData
import com.example.notesapp.Data
import com.example.notesapp.room.Dao
import kotlinx.coroutines.flow.Flow

class Repository(
    private val dao: Dao
) {
    suspend fun insert(data: Data) {
        dao.insert(data)
    }

    suspend fun update(data: Data) {
        dao.update(data)
    }

    suspend fun delete(data: Data) {
        dao.delete(data)
    }

    suspend fun deleteAll() {
        dao.deleteAllNotes()
    }

    val getAllNotes: LiveData<List<Data>> = dao.getAllNotes()

    fun searchDatabase(searchQuery: String): LiveData<List<Data>> {
      return  dao.searchDatabase(searchQuery)
    }

}