package com.example.domain.model

import java.util.*

data class ToDoItem(
    val id: Long,
    val title: String,
    val description: String,
    val date: Date
)
