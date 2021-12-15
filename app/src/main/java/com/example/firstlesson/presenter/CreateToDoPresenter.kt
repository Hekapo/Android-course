package com.example.firstlesson.presenter

import android.content.Context
import com.example.data.repository.ToDoRepositoryImpl
import com.example.data.room.ToDoDatabase
import com.example.domain.domain.SaveToDoUseCase
import com.example.domain.domain.UpdateToDoUseCase
import com.example.firstlesson.view.CreateToDoView

class CreateToDoPresenter(private val createToDoView: CreateToDoView, context: Context) {
    private var repositoryImpl: ToDoRepositoryImpl
    private var saveToDoUseCase: SaveToDoUseCase
    private var updateToDoUseCase: UpdateToDoUseCase

    init {
        val dao = ToDoDatabase.invoke(context).toDoDAO()
        repositoryImpl = ToDoRepositoryImpl(dao)
        saveToDoUseCase = SaveToDoUseCase(repositoryImpl)
        updateToDoUseCase = UpdateToDoUseCase(repositoryImpl)

    }

    fun updateToDo(title: String, desc: String, args: Long?) {
        if (updateToDoUseCase.execute(title, desc, args)) {
            createToDoView.navigateBack()
        } else {
            createToDoView.showToast("ToDo does not exist")
        }
    }

    fun createToDo(title: String, desc: String) {
        if (saveToDoUseCase.execute(title, desc)) {
            createToDoView.navigateBack()
        }
    }
}
