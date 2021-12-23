package com.example.domain.usecase

import com.example.domain.repository.ToDoRepository

class DeleteAllToDoUseCase(private val repository: ToDoRepository) {

    suspend fun execute() = repository.deleteAllToDo()

}
