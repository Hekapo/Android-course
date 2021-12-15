package com.example.domain.domain

import com.example.domain.model.ToDoItem
import com.example.domain.repository.ToDoRepository
import com.example.domain.validate

class SaveToDoUseCase(private val repository: ToDoRepository) {

    fun execute(title: String, desc: String): Boolean {
        if (title.validate() && desc.validate()) {
            repository.addNewToDo(ToDoItem(0, title, desc))
            return true
        }
        return false
    }
}
