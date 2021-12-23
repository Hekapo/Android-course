package com.example.domain.usecase

import com.example.domain.model.ToDoItem
import com.example.domain.repository.ToDoRepository

class DeleteOneToDoUseCase(private val repository: ToDoRepository) {

    suspend fun execute(toDoItem: ToDoItem): List<ToDoItem> {
        repository.deleteToDo(toDoItem)
        return repository.getAllToDo()
    }

}
