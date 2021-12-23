package com.example.firstlesson.view

import com.example.domain.model.ToDoItem

interface CreateToDoView {

    fun navigateBack()
    fun showToast(message: String)
    fun showCreateButton()
    fun setInitialText(todoItem: ToDoItem)
    fun showUpdateButton()

}
