package com.example.firstlesson.presenter

import android.os.Bundle
import com.example.data.DatabaseProvider
import com.example.data.repository.ToDoRepositoryImpl
import com.example.domain.model.ToDoItem
import com.example.domain.usecase.DeleteAllToDoUseCase
import com.example.domain.usecase.DeleteOneToDoUseCase
import com.example.domain.usecase.GetAllToDoUseCase
import com.example.firstlesson.view.ToDoListView

class ToDoListPresenter(
    private val toDoListView: ToDoListView,
    private val databaseProvider: DatabaseProvider
) {

    private val repositoryImpl by lazy {
        databaseProvider.provideDataBase()
        val dao = databaseProvider.provideDao()
        ToDoRepositoryImpl(dao)
    }

    private val getAllToDoUseCase by lazy { GetAllToDoUseCase(repositoryImpl) }
    private val deleteAllToDoUseCase by lazy { DeleteAllToDoUseCase(repositoryImpl) }
    private val deleteOneToDoUseCase by lazy { DeleteOneToDoUseCase(repositoryImpl) }

    fun loadToDo() {
        val listToDo = getAllToDoUseCase.execute()

        if (listToDo.isNotEmpty()) {
            toDoListView.hideEmptyData()
        } else {
            toDoListView.showEmptyData()
        }
        toDoListView.showAllToDo(listToDo)

    }

    fun navigate(destination: Int, bundle: Bundle?) {
        toDoListView.navigateTo(destination, bundle)
    }

    fun deleteAll() {
        deleteAllToDoUseCase.execute()
        toDoListView.showAllToDo(emptyList())
        toDoListView.showEmptyData()

    }

    fun delete(toDoItem: ToDoItem) {
        val listToDo = deleteOneToDoUseCase.execute(toDoItem)

        if (listToDo.isNotEmpty()) {
            toDoListView.hideEmptyData()
        } else {
            toDoListView.showEmptyData()
        }
        toDoListView.showAllToDo(listToDo)

    }

}
