package com.example.domain.repository

import com.example.domain.model.ToDoItem

interface BundleRepository {

    fun saveId(toDoItem: ToDoItem)

    fun getId(): Long?

    fun clearStorage()

}
