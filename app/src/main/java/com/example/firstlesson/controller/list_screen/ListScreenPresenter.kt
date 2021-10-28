package com.example.firstlesson.controller.list_screen

import android.os.Bundle
import com.example.firstlesson.model.WaifuService

class ListScreenPresenter(private val listScreenView: ListScreenView) {

    fun getAllWaifus() {
        val listWaifu = WaifuService.getAllWaifus()

        listScreenView.setWaifus(listWaifu)

    }

    fun navigateTo(destination: Int, args: Bundle) {
        listScreenView.navigate(destination,args)
    }
}
