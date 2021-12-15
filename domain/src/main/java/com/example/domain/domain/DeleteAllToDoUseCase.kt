package com.example.domain.domain

import com.example.domain.repository.ToDoRepository

class DeleteAllToDoUseCase(private val repository: ToDoRepository) {

    fun execute() = repository.deleteAllToDo()

}
