package com.example.firstlesson.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.firstlesson.R
import com.example.firstlesson.media_player.MusicService
import com.example.firstlesson.models.Song
import com.example.firstlesson.models.State
import androidx.media.app.NotificationCompat.MediaStyle as NotificationCompatMediaStyle


class SongNotification(val context: Context) {
    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
    private lateinit var nBuilder: NotificationCompat.Builder
    private lateinit var notification: Notification
    private val bundle by lazy { Bundle() }


    private val playSong = Intent(context, MusicService::class.java).apply {
        action = "PLAY"
    }.let {
        PendingIntent.getService(
            context,
            2,
            it,
            0
        )
    }
    private val previousSong: PendingIntent = Intent(context, MusicService::class.java).apply {
        action = "PREV"
    }.let {
        PendingIntent.getService(
            context,
            1,
            it,
            0
        )
    }

    private val nextSong: PendingIntent = Intent(context, MusicService::class.java).apply {
        action = "NEXT"

    }.let {
        PendingIntent.getService(
            context,
            3,
            it,
            0
        )
    }


    fun createNotification(song: Song) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                notificationManager.getNotificationChannel("CHANNEL_ID") ?: NotificationChannel(
                    "CHANNEL_ID",
                    name,
                    importance
                ).apply {
                    description = descriptionText
                }
            notificationManager.createNotificationChannel(channel)
        }

        val cover = BitmapFactory.decodeResource(context.resources, song.cover)
        bundle.putInt("SONG", song.id)
        val pendingIntent = NavDeepLinkBuilder(context)
            .setArguments(bundle)
            .setGraph(R.navigation.my_nav)
            .setArguments(bundle)
            .setDestination(R.id.musicScreenFragment)
            .createPendingIntent()

        nBuilder = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_baseline_pause_24)
            .addAction(R.drawable.ic_prev, "Previous", previousSong)
            .addAction(R.drawable.ic_baseline_pause_24, "Play", playSong)
            .addAction(R.drawable.ic_next, "Next", nextSong)
            .setContentTitle(song.audioTitle)
            .setStyle(NotificationCompatMediaStyle())
            .setLargeIcon(cover)
            .setSilent(true)
    }

    @SuppressLint("RestrictedApi")
    fun build(song: Song) {

        if (song.state == State.PLAY) {
            song.state = State.PAUSE
        } else {
            song.state = State.PLAY

        }


        if (song.state == State.PLAY) {
            nBuilder.setSmallIcon(R.drawable.ic_baseline_pause_24)
            nBuilder.mActions[1] =
                NotificationCompat.Action(R.drawable.ic_baseline_pause_24, "Play", playSong)
            notification = nBuilder.build()

        } else {
            nBuilder.setSmallIcon(R.drawable.ic_play)
            nBuilder.mActions[1] = NotificationCompat.Action(R.drawable.ic_play, "Play", playSong)
            notification = nBuilder.build()

        }

        notificationManager.notify(1, notification)

    }

    public fun createNotification(song: Song, image: Int, title: String) {
        val builder = nBuilder
            .clearActions()

    }

}
