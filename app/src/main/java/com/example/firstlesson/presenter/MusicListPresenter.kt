package com.example.firstlesson.presenter

import android.os.Bundle
import com.example.firstlesson.media_player.MusicService
import com.example.firstlesson.models.Song
import com.example.firstlesson.repository.SongRepository
import com.example.firstlesson.repository.SongRepositoryImpl
import com.example.firstlesson.view.MusicListView

class MusicListPresenter(
    private val musicListView: MusicListView,
) {

    private val songRepository: SongRepository = SongRepositoryImpl
    private val songs = songRepository.getSongs()

    fun getSongs() = musicListView.setSongs(songs)

    fun play(song: Song,binder:MusicService.LocaleBinder) = binder.play(song)

    fun navigate(destination: Int, bundle: Bundle) = musicListView.navigateTo(destination, bundle)

}
