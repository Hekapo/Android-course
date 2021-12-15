package com.example.data.mappers

import com.example.data.room.entity.ToDo
import com.example.domain.model.ToDoItem

class ToDoMapper {

    fun toDoToEntity(toDoItem: ToDoItem): ToDo {
        return ToDo(toDoItem.id, toDoItem.title, toDoItem.description, toDoItem.date)
    }

    fun entityToToDo(toDo: ToDo): ToDoItem {
        return ToDoItem(toDo.id, toDo.title, toDo.description, toDo.date)
    }
}
