package com.example.firstlesson.controller.list_screen

import android.os.Bundle
import com.example.firstlesson.model.Waifu

interface ListScreenView {
    fun navigate(id: Int, arg: Bundle?)
    fun setWaifus(list: List<Waifu>)
}
