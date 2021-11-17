package com.example.firstlesson.presenters

import com.example.firstlesson.AddNewsDialog
import com.example.firstlesson.models.News
import com.example.firstlesson.repository.NewsRepository
import com.example.firstlesson.views.list_view.ListView

class ListViewPresenter(private val listView: ListView) : AddNewsDialog.Listener {

    private val newsRepository = NewsRepository

    fun getNews() {
        val news = newsRepository.getAllNews()
        listView.setData(news)
    }

    override fun okClicked(title: String, desc: String, pos: String) {
        newsRepository.addNews(pos, desc, title)
        val news = newsRepository.getAllNews()
        listView.setData(news)

    }

    fun delete(news: News) {
        newsRepository.deleteNews(news)
        val listOfNews = newsRepository.getAllNews()
        listView.setData(listOfNews)

    }

}
