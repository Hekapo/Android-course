package com.example.data.mappers

import com.example.data.room.entity.ToDo
import com.example.data.storage.model.ToDoModel
import com.example.domain.model.ToDoItem


fun ToDoItem.mapToEntity(): ToDo {
    return ToDo(this.id, this.title, this.description, this.date)
}

fun ToDo.mapEntityToToDoItem(): ToDoItem {
    return ToDoItem(this.id, this.title, this.description, this.date)
}

fun ToDoItem.mapToToDoModel(): ToDoModel {
    return ToDoModel(this.id, this.title, this.description, this.date)
}
