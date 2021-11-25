package com.example.firstlesson.repository

import android.content.Context
import com.example.firstlesson.utils.Constants
import java.util.*

class TimeRepository {

    fun saveTime(calendar: Calendar, context: Context) {
        val sharedPreferences = context.getSharedPreferences(Constants.TIME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putLong(Constants.TIME_IN_MILLISECONDS, calendar.timeInMillis)
            .apply()

    }

    fun getTime(context: Context): Long {
        val sharedPreferences = context.getSharedPreferences(Constants.TIME, Context.MODE_PRIVATE)
        return sharedPreferences.getLong(Constants.TIME_IN_MILLISECONDS, 1)
    }
}
