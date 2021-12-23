package com.example.domain.usecase

import com.example.domain.repository.ToDoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetOneToDoUseCase(
    private val repository: ToDoRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun execute(id: Long?) = coroutineScope {
        val todo = async(dispatcher) { repository.getOneToDo(id) }
        todo.await()
    }

}

