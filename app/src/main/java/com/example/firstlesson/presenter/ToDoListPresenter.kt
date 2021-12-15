package com.example.firstlesson.presenter

import android.content.Context
import android.os.Bundle
import com.example.data.repository.ToDoRepositoryImpl
import com.example.data.room.ToDoDatabase
import com.example.domain.usecase.DeleteAllToDoUseCase
import com.example.domain.usecase.DeleteOneToDoUseCase
import com.example.domain.usecase.GetAllToDoUseCase
import com.example.domain.model.ToDoItem
import com.example.firstlesson.view.ToDoListView

class ToDoListPresenter(private val toDoListView: ToDoListView, context: Context) {
    private val repositoryImpl: ToDoRepositoryImpl
    private val getAllToDoUseCase: GetAllToDoUseCase
    private val deleteAllToDoUseCase: DeleteAllToDoUseCase
    private val deleteOneToDoUseCase: DeleteOneToDoUseCase

    init {
        val dao = ToDoDatabase.invoke(context)
        repositoryImpl = ToDoRepositoryImpl(dao.toDoDAO())
        getAllToDoUseCase = GetAllToDoUseCase(repositoryImpl)
        deleteAllToDoUseCase = DeleteAllToDoUseCase(repositoryImpl)
        deleteOneToDoUseCase = DeleteOneToDoUseCase(repositoryImpl)
    }

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
