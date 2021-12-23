package com.example.data.repository

import com.example.data.mappers.mapEntityToToDoItem
import com.example.data.mappers.mapToEntity
import com.example.data.room.dao.ToDoDAO
import com.example.domain.model.ToDoItem
import com.example.domain.repository.ToDoRepository

class ToDoRepositoryImpl(private val dao: ToDoDAO) : ToDoRepository {

    override fun deleteToDo(toDoItem: ToDoItem) =
        dao.deleteToDo(toDoItem.mapToEntity())

    override fun getOneToDo(id: Long?) =
        dao.getOneToDo(id.toString()).mapEntityToToDoItem()

    override fun deleteAllToDo() =
        dao.deleteAllToDo()

    override fun addNewToDo(toDoItem: ToDoItem) =
        dao.addNewToDo(toDoItem.mapToEntity())

    override fun updateToDo(toDoItem: ToDoItem) =
        dao.updateToDo(toDoItem.mapToEntity())

    override fun getAllToDo(): List<ToDoItem> {
        val list = mutableListOf<ToDoItem>()
        dao.getAllToDo().forEach {
            list.add(ToDoItem(it.id, it.title, it.description, it.date))
        }

        return list
    }

}
