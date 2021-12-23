package com.example.domain.usecase

import com.example.domain.model.ToDoItem
import com.example.domain.repository.ToDoRepository
import java.util.*

class UpdateToDoUseCase(private val repository: ToDoRepository) {

    suspend fun execute(title: String, desc: String, id: Long?, date: Date) =
        id?.let { ToDoItem(it, title, desc, date) }?.let { repository.updateToDo(it) }

}
