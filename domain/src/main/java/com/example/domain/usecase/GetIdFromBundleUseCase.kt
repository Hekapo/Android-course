package com.example.domain.usecase

import com.example.domain.repository.BundleRepository

class GetIdFromBundleUseCase(private val bundleRepository: BundleRepository) {

    fun execute() = bundleRepository.getId()

}
