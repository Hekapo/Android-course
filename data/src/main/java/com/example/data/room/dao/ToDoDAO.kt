package com.example.data.room.dao

import androidx.room.*
import com.example.data.room.entity.ToDo

@Dao
interface ToDoDAO {
    @Delete
    suspend fun deleteToDo(toDo: ToDo)

    @Query("DELETE FROM todo")
    suspend fun deleteAllToDo()

    @Insert
    suspend fun addNewToDo(toDo: ToDo): Long

    @Update
    suspend fun updateToDo(toDoItem: ToDo): Int

    @Query("SELECT * FROM todo")
    suspend fun getAllToDo(): List<ToDo>

    @Query("SELECT id, title, description, date FROM todo WHERE :id = id")
    suspend fun getOneToDo(id: String): ToDo
}
