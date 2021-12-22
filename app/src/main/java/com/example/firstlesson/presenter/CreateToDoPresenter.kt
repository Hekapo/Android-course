package com.example.firstlesson.presenter

import com.example.data.DatabaseProvider
import com.example.data.repository.ToDoRepositoryImpl
import com.example.domain.usecase.GetOneToDoUseCase
import com.example.domain.usecase.SaveToDoUseCase
import com.example.domain.usecase.UpdateToDoUseCase
import com.example.firstlesson.view.CreateToDoView
import java.util.*

class CreateToDoPresenter(
    private val createToDoView: CreateToDoView,
    private val databaseProvider: DatabaseProvider
) {

    private val repository by lazy {
        databaseProvider.provideDataBase()
        val dao = databaseProvider.provideDao()
        ToDoRepositoryImpl(dao)
    }

    private val saveToDoUseCase by lazy { SaveToDoUseCase(repository) }
    private val updateToDoUseCase by lazy { UpdateToDoUseCase(repository) }
    private val getOneToDoUseCase by lazy { GetOneToDoUseCase(repository) }

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
