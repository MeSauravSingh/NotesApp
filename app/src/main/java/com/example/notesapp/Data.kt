package com.example.notesapp

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class Data (

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val desc: String,

    ):Parcelable