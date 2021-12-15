package com.example.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    val id: Long,
    val title: String,
    val description: String
)

@Fts4(contentEntity = ToDo::class)
@Entity(tableName = "todo_fts")
data class ToDoFts(
    val title: String,
    val description: String
)