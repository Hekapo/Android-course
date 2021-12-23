package com.example.firstlesson.presenter

import com.example.data.repository.BundleRepositoryImpl
import com.example.data.repository.ToDoRepositoryImpl
import com.example.data.room.DatabaseProvider
import com.example.data.storage.BundleIdStorage
import com.example.domain.model.ToDoItem
import com.example.domain.usecase.*
import com.example.firstlesson.view.ToDoListView
import kotlinx.coroutines.*

class ToDoListPresenter(
    private val toDoListView: ToDoListView,
    private val databaseProvider: DatabaseProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val scope: CoroutineScope
) {

    private val bundleStorage by lazy { BundleIdStorage() }

    private val bundleRepository by lazy { BundleRepositoryImpl(bundleStorage) }

    private val saveIdToBundleUseCase by lazy { SaveIdToBundleUseCase(bundleRepository) }
    private val clearStorageUseCase by lazy { ClearStorageUseCase(bundleRepository) }

    private val repositoryImpl by lazy {
        databaseProvider.provideDataBase()
        val dao = databaseProvider.provideDao()
        ToDoRepositoryImpl(dao)
    }

    private val getAllToDoUseCase by lazy { GetAllToDoUseCase(repositoryImpl) }
    private val deleteAllToDoUseCase by lazy { DeleteAllToDoUseCase(repositoryImpl) }
    private val deleteOneToDoUseCase by lazy { DeleteOneToDoUseCase(repositoryImpl) }

    fun clearStorage() {
        clearStorageUseCase.execute()
    }

    fun loadToDo() {
        var listToDo: List<ToDoItem>
        scope.launch {
            listToDo = getAllToDoUseCase.execute()
            withContext(dispatcher) {
                if (listToDo.isNotEmpty()) {
                    toDoListView.hideEmptyData()
                } else {
                    toDoListView.showEmptyData()
                }
                toDoListView.showAllToDo(listToDo)
            }
        }

    }

    fun navigate(destination: Int, toDoItem: ToDoItem?) {
        saveIdToBundleUseCase.execute(toDoItem)
        toDoListView.navigateTo(destination)
    }

    fun deleteAll() {
        scope.launch {
            deleteAllToDoUseCase.execute()
            withContext(dispatcher) {
                toDoListView.showAllToDo(emptyList())
                toDoListView.showEmptyData()

            }
        }

    }

    fun delete(toDoItem: ToDoItem) {
        scope.launch {
            val listToDo = deleteOneToDoUseCase.execute(toDoItem)
            withContext(dispatcher) {
                if (listToDo.isNotEmpty()) {
                    toDoListView.hideEmptyData()
                } else {
                    toDoListView.showEmptyData()
                }
                toDoListView.showAllToDo(listToDo)
            }
        }


    }

}
