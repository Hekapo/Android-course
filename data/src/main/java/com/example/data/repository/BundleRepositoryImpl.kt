package com.example.data.repository

import com.example.data.mappers.mapToToDoModel
import com.example.data.storage.BundleIdStorage
import com.example.domain.model.ToDoItem
import com.example.domain.repository.BundleRepository

class BundleRepositoryImpl(private val bundleIdStorage: BundleIdStorage) : BundleRepository {

    override fun saveId(toDoItem: ToDoItem) = bundleIdStorage.saveId(toDoItem.mapToToDoModel())

    override fun getId() = bundleIdStorage.getId()

    override fun clearStorage() = bundleIdStorage.clearStorage()

}
