package com.example.domain.usecase

import com.example.domain.repository.ToDoRepository

class GetAllToDoUseCase(private val repository: ToDoRepository) {

    fun execute() = repository.getAllToDo()

}
