package com.example.domain.usecase

import com.example.domain.repository.ToDoRepository

class GetOneToDoUseCase(private val repository: ToDoRepository) {

    fun execute(id: Long?) = repository.getOneToDo(id)

}

