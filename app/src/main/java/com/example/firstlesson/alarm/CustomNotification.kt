package com.example.firstlesson.alarm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.firstlesson.R
import com.example.firstlesson.SecondActivity
import com.example.firstlesson.utils.Constants

class CustomNotification {

    fun createNotification(context: Context) {
        val intent = Intent(context, SecondActivity::class.java).let {
            PendingIntent.getActivities(
                context,
                123,
                arrayOf(it),
                PendingIntent.FLAG_ONE_SHOT
            )
        }

        val builder = NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.leather)
            .setContentTitle("Leather man")
            .setShowWhen(false)
            .setAutoCancel(true)
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(
                    "TRY HARD WORK HARD"
                )
            )
            .setContentIntent(intent)
            .setContentText("Desc")
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.notify(1, builder.build())
    }
}
