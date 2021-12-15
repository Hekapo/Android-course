package com.example.domain.repository

import com.example.domain.model.ToDoItem

interface ToDoRepository {

    fun addNewToDo(toDoItem: ToDoItem): Long

    fun updateToDo(toDoItem: ToDoItem): Int

    fun getAllToDo(): List<ToDoItem>

    fun deleteAllToDo()

    fun deleteToDo(toDoItem: ToDoItem)

    fun filter(title: String): ToDoItem?
}
