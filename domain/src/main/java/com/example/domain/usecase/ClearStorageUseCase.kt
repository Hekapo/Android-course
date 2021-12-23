package com.example.domain.usecase

import com.example.domain.repository.BundleRepository

class ClearStorageUseCase(private val bundleRepository: BundleRepository) {

    fun execute() = bundleRepository.clearStorage()
}
