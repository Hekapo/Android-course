package com.example.firstlesson.media_player

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.annotation.RawRes
import com.example.firstlesson.models.Song
import com.example.firstlesson.models.State
import com.example.firstlesson.notification.SongNotification

interface IntentActionListener {
    fun actionNext()
    fun actionPrev()
    fun actionPause()

}

class MusicService : Service() {

    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private lateinit var songNotification: SongNotification
    private lateinit var intentActionListener: IntentActionListener
    private lateinit var song: Song

    inner class LocaleBinder : Binder() {

        fun play(song: Song) {
            this@MusicService.song = song
            playLocaleMusic(song.audio)
            play()
            songNotification.createNotification(this@MusicService.song)

            songNotification.build(song)

        }

        fun pause(): Boolean {

            songNotification.build(song)

            return this@MusicService.pause()

        }

        fun end() {

        }

        fun setIntentActionListener(intentActionListener: IntentActionListener) {
            this@MusicService.setIntentActionListener(intentActionListener)
        }

        fun stop() = this@MusicService.stop()

    }

    override fun onBind(intent: Intent?): IBinder = LocaleBinder()

    override fun onCreate() {
        super.onCreate()
        songNotification = SongNotification(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "NEXT" -> {
                intentActionListener.actionNext()
            }
            "PREV" -> {
                intentActionListener.actionPrev()
            }
            "PLAY" -> {
                intentActionListener.actionPause()
            }

        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun pause(): Boolean {
        return if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            true

        } else {
            mediaPlayer.start()
            false
        }
    }

    private fun setIntentActionListener(intentActionListener: IntentActionListener) {
        this.intentActionListener = intentActionListener

    }

    private fun stop() = mediaPlayer.stop()
    private fun play() = mediaPlayer.start()

    private var temp = 0

    private fun playLocaleMusic(@RawRes song: Int) {
        if (mediaPlayer.isPlaying) {
            if (temp != song) {
                mediaPlayer.stop()
                mediaPlayer = MediaPlayer.create(applicationContext, song)
            }
            temp = song
        } else if (!mediaPlayer.isPlaying) {
            mediaPlayer = MediaPlayer.create(applicationContext, song)
            temp = song

        }
        mediaPlayer.run {
            setOnCompletionListener {
                stop() // or call next() for change track
            }
        }

    }



}
