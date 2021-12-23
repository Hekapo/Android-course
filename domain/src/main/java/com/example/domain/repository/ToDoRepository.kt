package com.example.domain.repository

import com.example.domain.model.ToDoItem

interface ToDoRepository {

    suspend fun addNewToDo(toDoItem: ToDoItem): Long

    suspend fun updateToDo(toDoItem: ToDoItem): Int

    suspend fun getAllToDo(): List<ToDoItem>

    suspend fun deleteAllToDo()

    suspend fun deleteToDo(toDoItem: ToDoItem)

    suspend fun getOneToDo(id: Long?): ToDoItem
}
