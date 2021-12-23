package com.example.domain.usecase

import com.example.domain.model.ToDoItem
import com.example.domain.repository.ToDoRepository
import com.example.domain.validate
import java.util.*

class SaveToDoUseCase(private val repository: ToDoRepository) {

   suspend fun execute(title: String, desc: String, date: Date): Boolean {
        if (title.validate() && desc.validate()) {
            repository.addNewToDo(ToDoItem(0, title, desc, date))
            return true
        }
        return false
    }
}
