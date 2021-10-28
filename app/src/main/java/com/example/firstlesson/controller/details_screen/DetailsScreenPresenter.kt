package com.example.firstlesson.controller.details_screen

import com.example.firstlesson.model.WaifuService

class DetailsScreenPresenter(private val detailsScreenView: DetailsScreenView) {

    fun getWaifu(id: Int) {
        WaifuService.getAllWaifus().forEach {
            if (it.id == id) {
                detailsScreenView.setWaifu(it)
            }
        }


    }

    fun navigateTo(destination:Int) {
        detailsScreenView.navigate(destination)
    }
}
