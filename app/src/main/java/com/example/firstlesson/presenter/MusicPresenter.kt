package com.example.firstlesson.presenter

import android.util.Log
import com.example.firstlesson.media_player.IntentActionListener
import com.example.firstlesson.media_player.MusicService
import com.example.firstlesson.models.Song
import com.example.firstlesson.models.State
import com.example.firstlesson.repository.SongRepository
import com.example.firstlesson.repository.SongRepositoryImpl
import com.example.firstlesson.view.MusicView

class MusicPresenter(
    private val musicView: MusicView,
    private val binder: MusicService.LocaleBinder?
) : IntentActionListener {

    private val songRepository: SongRepository = SongRepositoryImpl
    private lateinit var currentSong: Song

    init {
        Log.e("TAG", "setIntentActionListener")
    }

    fun getMusicById(id: Int) {
        val song = songRepository.getSongById(id)
        musicView.setMusic(song)
        currentSong = song
        setButtonState(currentSong)

    }

    private fun setButtonState(song: Song) {
        when (song.state) {
            State.STOP -> {}
            State.PLAY -> {
                musicView.setPauseButton()
                musicView.setMusic(song)
            }
            State.PAUSE -> {
                musicView.setPlayButton()
                musicView.setMusic(song)

            }
        }

    }

    fun nextSong() {
        val song = songRepository.nextSong(currentSong)
        binder?.play(song)
        setButtonState(song)
        currentSong.state = State.PAUSE
        currentSong = song

    }

    fun prevSong() {
        val song = songRepository.prevSong(currentSong)
        binder?.play(song)
        setButtonState(song)
        currentSong.state = State.PAUSE
        currentSong = song

    }

    fun pauseSong() {
        if (binder?.pause() == true) {
            currentSong.state = State.PAUSE
        } else {
            currentSong.state = State.PLAY

        }
        setButtonState(currentSong)

    }

    override fun actionNext() {
        nextSong()
    }

    override fun actionPrev() {
        prevSong()
    }

    override fun actionPause() {
        pauseSong()
    }
}
