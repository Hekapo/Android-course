package com.example.domain.domain

import com.example.domain.model.ToDoItem
import com.example.domain.repository.ToDoRepository

class DeleteOneToDoUseCase(private val repository: ToDoRepository) {

    fun execute(toDoItem: ToDoItem): List<ToDoItem> {
        repository.deleteToDo(toDoItem)
        return repository.getAllToDo()
    }

}
