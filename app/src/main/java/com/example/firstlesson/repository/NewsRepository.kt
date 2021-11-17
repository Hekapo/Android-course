package com.example.firstlesson.repository

import com.example.firstlesson.models.News
import com.example.firstlesson.repository.NewsRepository.Data.news
import com.example.firstlesson.repository.NewsRepository.Data.title

object NewsRepository {

    private var listOfNews = mutableListOf<News>()

    init {
        listOfNews = (1..30).map {
            News(
                id = it,
                title = title[it % title.size],
                description = news[it % news.size]
            )
        }.toMutableList()

    }

    fun getAllNews(): List<News> {
        return listOfNews

    }

    fun addNews(position: String, description: String, title: String) {
        if (position.isEmpty() || position.toInt() > listOfNews.size) {
            val pos = listOfNews.size
            listOfNews = ArrayList(listOfNews)
            listOfNews.add(
                pos,
                News(pos, title, description)
            )

        } else {
            val pos = position.toInt()
            listOfNews = ArrayList(listOfNews)
            listOfNews.add(
                pos,
                News(pos, title, description)
            )
        }

    }

    fun deleteNews(news: News) {
        listOfNews = ArrayList(listOfNews)
        listOfNews.remove(news)

    }

    object Data {
        val title =
            listOf("Coronavirus", "Zombies", "Stay home", "Eat each other", "Be careful")

        val news = listOf(
            "Llllet me die. The song of 2021",
            "Brainzzz are very tasty, said our new President",
            "Stay home. Gamers are very happy",
            "Zombies everywhere. If you get hungry don't eat them",
            "Don't play with wolves and bears. They might eat you"
        )
    }
}

