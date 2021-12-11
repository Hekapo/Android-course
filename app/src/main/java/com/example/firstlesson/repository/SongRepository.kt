package com.example.firstlesson.repository

import com.example.firstlesson.models.Song

interface SongRepository {
    fun getSongs(): List<Song>
    fun nextSong(currentSong: Song): Song
    fun prevSong(currentSong: Song): Song
    fun getSongById(id: Int): Song


}
