package com.example.firstlesson.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.firstlesson.alarm.CustomNotification

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        val customNotification = CustomNotification()
        if (context != null) {
            customNotification.createNotification(context)
        }
    }
}
