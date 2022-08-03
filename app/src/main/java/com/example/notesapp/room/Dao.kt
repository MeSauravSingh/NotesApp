package com.example.notesapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.notesapp.Data

@Dao
interface Dao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: Data)

    @Update
    suspend fun update(data: Data)

    @Delete
    suspend fun delete(data: Data)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Data>>

    @Query("SELECT * FROM notes_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Data>>


}