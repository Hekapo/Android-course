package com.example.domain.usecase

import com.example.domain.model.ToDoItem
import com.example.domain.repository.ToDoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetAllToDoUseCase(
    private val repository: ToDoRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun execute(): List<ToDoItem> = coroutineScope {
        val todoList = async(dispatcher) {
            repository.getAllToDo()
        }
        todoList.await()

    }

}
