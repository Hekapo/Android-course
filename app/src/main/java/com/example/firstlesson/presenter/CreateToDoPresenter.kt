package com.example.firstlesson.presenter

import android.content.Context
import com.example.data.repository.ToDoRepositoryImpl
import com.example.data.room.ToDoDatabase
import com.example.domain.usecase.GetOneToDoUseCase
import com.example.domain.usecase.SaveToDoUseCase
import com.example.domain.usecase.UpdateToDoUseCase
import com.example.firstlesson.view.CreateToDoView
import java.util.*

class CreateToDoPresenter(private val createToDoView: CreateToDoView, context: Context) {
    private val repositoryImpl: ToDoRepositoryImpl
    private val saveToDoUseCase: SaveToDoUseCase
    private val updateToDoUseCase: UpdateToDoUseCase
    private val getOneToDoUseCase: GetOneToDoUseCase

    init {
        val dao = ToDoDatabase.invoke(context).toDoDAO()
        repositoryImpl = ToDoRepositoryImpl(dao)
        saveToDoUseCase = SaveToDoUseCase(repositoryImpl)
        updateToDoUseCase = UpdateToDoUseCase(repositoryImpl)
        getOneToDoUseCase = GetOneToDoUseCase(repositoryImpl)

    }

    fun getOneToDo(id: Long?) {
        if (id == null) {
            createToDoView.showButton()
        } else {
            val result = getOneToDoUseCase.execute(id)
            createToDoView.setInitialText(result)
        }

        return

    }

    fun updateToDo(title: String, desc: String, args: Long?, date: Date) {
        if (updateToDoUseCase.execute(title, desc, args, date)) {
            createToDoView.navigateBack()
        } else {
            createToDoView.showToast("ToDo does not exist")
        }
    }

    fun createToDo(title: String, desc: String, date: Date) {
        if (saveToDoUseCase.execute(title, desc, date)) {
            createToDoView.navigateBack()
        }
    }
}
