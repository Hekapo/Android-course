package com.example.firstlesson.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.firstlesson.alarm.CustomAlarm
import com.example.firstlesson.repository.TimeRepository

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            val timeRepository = TimeRepository()
            val alarm = CustomAlarm()
            alarm.setAlarm(context, timeRepository.getTime(context))

        }
    }

}
