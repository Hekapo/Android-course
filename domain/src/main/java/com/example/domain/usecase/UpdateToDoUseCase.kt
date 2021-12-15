package com.example.domain.usecase

import com.example.domain.model.ToDoItem
import com.example.domain.repository.ToDoRepository
import java.util.*

class UpdateToDoUseCase(private val repository: ToDoRepository) {

    fun execute(title: String, desc: String, args: Long?, date: Date): Boolean {

        if (args != null) {
            repository.updateToDo(ToDoItem(args, title, desc, date))
            return true
        } else {
            val result = repository.filter(title)
            val item = ToDoItem(0, title, desc, date)
            if (result != null) {
                if (result.title == item.title) {
                    repository.updateToDo(
                        ToDoItem(
                            result.id,
                            item.title,
                            item.description,
                            item.date
                        )
                    )
                    return true
                }
            } else {
                return false

            }
        }
        return false

    }
}
