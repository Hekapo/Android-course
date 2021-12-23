package com.example.data.storage

import com.example.data.storage.model.ToDoModel

interface ToDoItemStorage {

    fun saveId(toDoModel: ToDoModel)

    fun getId(): Long

    fun clearStorage()
}
