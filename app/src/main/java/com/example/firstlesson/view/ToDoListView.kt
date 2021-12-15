package com.example.firstlesson.view

import android.os.Bundle
import com.example.domain.model.ToDoItem

interface ToDoListView {
    fun showAllToDo(toDoItem: List<ToDoItem>)

    fun navigateTo(destination: Int, bundle: Bundle?)

    fun showEmptyData()
    fun hideEmptyData()
}
