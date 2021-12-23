package com.example.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val date: Date
)
