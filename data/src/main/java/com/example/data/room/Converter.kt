package com.example.data.room

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Converter {

    @SuppressLint("SimpleDateFormat")
    @TypeConverter
    fun fromDate(date: Date): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.format(date.time)

    }

    @SuppressLint("SimpleDateFormat")
    @TypeConverter
    fun toDate(dateString: String): Date {

        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString)
    }
}
