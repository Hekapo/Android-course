package com.example.firstlesson.view

import android.content.Context

interface HomeScreenView {
    fun setTimeText(time:String)

    fun showTimePicker()

    fun showToast(context: Context)
    fun hideToast(context: Context)

    fun setClickableIv()
    fun setNotClickableIv()


}
