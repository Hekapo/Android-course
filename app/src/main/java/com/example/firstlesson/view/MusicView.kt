package com.example.firstlesson.view

import com.example.firstlesson.models.Song

interface MusicView {
    fun setMusic(song: Song)
    fun setPauseButton()
    fun setPlayButton()
}
