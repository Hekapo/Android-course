package com.example.data.room.dao

import androidx.room.*
import com.example.data.room.entity.ToDo

@Dao
interface ToDoDAO {
    @Delete
    fun deleteToDo(toDo: ToDo)

    @Query("DELETE FROM todo")
    fun deleteAllToDo()

    @Insert
    fun addNewToDo(toDo: ToDo): Long

    @Update
    fun updateToDo(toDoItem: ToDo): Int

    @Query("SELECT * FROM todo")
    fun getAllToDo(): List<ToDo>

    @Query("SELECT id, title, description, date FROM todo WHERE :id = id")
    fun getOneToDo(id: String): ToDo
}
