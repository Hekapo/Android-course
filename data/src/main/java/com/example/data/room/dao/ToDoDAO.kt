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

    //    @Query("UPDATE todo SET description = :description WHERE title = :title ")

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateToDo(toDoItem: ToDo): Int

    @Query("SELECT * FROM todo")
    fun getAllToDo(): List<ToDo>

//    @Query("""SELECT * FROM todo WHERE title MATCH :query""")
//    fun isContains(query: String): List<ToDo>

    @Query(
        "SELECT rowid, todo_fts.title, todo_fts.description  FROM todo JOIN todo_fts ON todo.rowid == todo_fts.rowid WHERE todo_fts.title MATCH :search ORDER BY rowid"
    )
    fun filtered(search: String): ToDo?
}