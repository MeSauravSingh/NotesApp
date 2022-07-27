package com.example.localstorage

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


data class Data(
    var id: Int,
    val title: String,
    val desc: String,
)
