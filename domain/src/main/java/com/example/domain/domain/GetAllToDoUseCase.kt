package com.example.domain.domain

import com.example.domain.repository.ToDoRepository

class GetAllToDoUseCase(private val repository: ToDoRepository) {

    fun execute() = repository.getAllToDo()

}
