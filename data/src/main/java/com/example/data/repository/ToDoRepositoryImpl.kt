package com.example.data.repository

import com.example.data.mappers.ToDoMapper
import com.example.data.room.dao.ToDoDAO
import com.example.domain.model.ToDoItem
import com.example.domain.repository.ToDoRepository

class ToDoRepositoryImpl(private val dao: ToDoDAO) : ToDoRepository {

    override fun deleteToDo(toDoItem: ToDoItem) =
        dao.deleteToDo(ToDoMapper().toDoToEntity(toDoItem))

    override fun filter(title: String): ToDoItem? {
        val item = dao.filtered(title)
        if (item != null) {
            return ToDoItem(item.id, item.title, item.description)
        }
        return null
//        val list = mutableListOf<ToDoItem>()
//        dao.filtered(title).forEach {
//            list.add(ToDoItem(it.id, it.title, it.description))
//        }
//        return list
    }

    override fun deleteAllToDo() = dao.deleteAllToDo()

    override fun addNewToDo(toDoItem: ToDoItem) =
        dao.addNewToDo(ToDoMapper().toDoToEntity(toDoItem))

    override fun updateToDo(toDoItem: ToDoItem) =
        dao.updateToDo(ToDoMapper().toDoToEntity(toDoItem))

    override fun getAllToDo(): List<ToDoItem> {
        val list = mutableListOf<ToDoItem>()
        dao.getAllToDo().forEach {
            list.add(ToDoItem(it.id, it.title, it.description))
        }

        return list
    }
}
