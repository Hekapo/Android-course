package com.example.domain.domain

import com.example.domain.model.ToDoItem
import com.example.domain.repository.ToDoRepository

class UpdateToDoUseCase(private val repository: ToDoRepository) {

    fun execute(title: String, desc: String, args: Long?): Boolean {

        if (args != null) {
            repository.updateToDo(ToDoItem(args, title, desc))
            return true
        } else {
            val result = repository.filter(title)
            val item = ToDoItem(0, title, desc)
            if (result != null) {
                if (result.title == item.title) {
                    repository.updateToDo(ToDoItem(result.id, item.title, item.description))
                    return true
                }
            } else {
                return false

            }
        }
        return false

    }
}
