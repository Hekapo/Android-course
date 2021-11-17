package com.example.firstlesson.views.list_view

import com.example.firstlesson.models.News

interface ListView {
    fun setData(list: List<News>)
    fun showDialog()
}
