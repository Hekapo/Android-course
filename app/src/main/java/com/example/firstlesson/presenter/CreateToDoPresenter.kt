package com.example.firstlesson.presenter

import com.example.data.repository.BundleRepositoryImpl
import com.example.data.repository.ToDoRepositoryImpl
import com.example.data.room.DatabaseProvider
import com.example.data.storage.BundleIdStorage
import com.example.domain.usecase.GetIdFromBundleUseCase
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

    private val bundleStorage by lazy {
        BundleIdStorage()
    }
    private val bundleRepository by lazy {
        BundleRepositoryImpl(bundleStorage)
    }
    private val getIdFromBundleUseCase by lazy {
        GetIdFromBundleUseCase(bundleRepository)
    }

    private val saveToDoUseCase by lazy { SaveToDoUseCase(repository) }
    private val updateToDoUseCase by lazy { UpdateToDoUseCase(repository) }
    private val getOneToDoUseCase by lazy { GetOneToDoUseCase(repository) }

    fun getOneToDo(): Long? {
        val id = getIdFromBundleUseCase.execute()
        if (id == 0L) {
            createToDoView.showCreateButton()
        } else {
            val result = getOneToDoUseCase.execute(id)
            createToDoView.showUpdateButton()
            createToDoView.setInitialText(result)
        }

        return id
    }

    fun updateToDo(title: String, desc: String, args: Long?, date: Date) {
        updateToDoUseCase.execute(title, desc, args, date)
        createToDoView.navigateBack()
    }

    fun createToDo(title: String, desc: String, date: Date) {
        if (saveToDoUseCase.execute(title, desc, date)) {
            createToDoView.navigateBack()
        } else {
            createToDoView.showToast("invalid input")
        }
    }
}
