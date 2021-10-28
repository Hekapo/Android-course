package com.example.firstlesson.controller.details_screen

import com.example.firstlesson.model.Waifu

interface DetailsScreenView {

    fun setWaifu(waifu: Waifu)
    fun navigate(destination: Int)
}
