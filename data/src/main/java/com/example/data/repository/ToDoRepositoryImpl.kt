package com.example.data.repository

import com.example.data.mappers.mapEntityToToDoItem
import com.example.data.mappers.mapToEntity
import com.example.data.room.dao.ToDoDAO
import com.example.domain.model.ToDoItem
import com.example.domain.repository.ToDoRepository

class ToDoRepositoryImpl(private val dao: ToDoDAO) : ToDoRepository {

    override suspend fun deleteToDo(toDoItem: ToDoItem) =
        dao.deleteToDo(toDoItem.mapToEntity())

    override suspend fun getOneToDo(id: Long?) =
        dao.getOneToDo(id.toString()).mapEntityToToDoItem()

    override suspend fun deleteAllToDo() =
        dao.deleteAllToDo()

    override suspend fun addNewToDo(toDoItem: ToDoItem) =
        dao.addNewToDo(toDoItem.mapToEntity())

    override suspend fun updateToDo(toDoItem: ToDoItem) =
        dao.updateToDo(toDoItem.mapToEntity())

    override suspend fun getAllToDo(): List<ToDoItem> {
        val list = mutableListOf<ToDoItem>()
        dao.getAllToDo().forEach {
            list.add(ToDoItem(it.id, it.title, it.description, it.date))
        }

        return list
    }

}
