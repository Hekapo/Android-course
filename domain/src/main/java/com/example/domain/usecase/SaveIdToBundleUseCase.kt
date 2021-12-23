package com.example.domain.usecase

import com.example.domain.model.ToDoItem
import com.example.domain.repository.BundleRepository

class SaveIdToBundleUseCase(private val bundleRepository: BundleRepository) {

    fun execute(toDoItem: ToDoItem?) = toDoItem?.let {
        bundleRepository.saveId(it)
    }
}
