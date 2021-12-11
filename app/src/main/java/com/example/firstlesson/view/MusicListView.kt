package com.example.firstlesson.view

import android.os.Bundle
import com.example.firstlesson.models.Song

interface MusicListView {
    fun setSongs(list: List<Song>)
    fun navigateTo(destination: Int, bundle: Bundle)

}
