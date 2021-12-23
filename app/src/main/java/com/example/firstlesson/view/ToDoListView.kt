package com.example.firstlesson.view

import com.example.domain.model.ToDoItem

interface ToDoListView {
    fun showAllToDo(toDoItem: List<ToDoItem>)

    fun navigateTo(destination: Int)

    fun showEmptyData()
    fun hideEmptyData()
}
