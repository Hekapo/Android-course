package com.example.firstlesson.presenter

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.firstlesson.alarm.CustomAlarm
import com.example.firstlesson.repository.TimeRepository
import com.example.firstlesson.time_picker.TimePicker
import com.example.firstlesson.utils.TimeRefactor
import com.example.firstlesson.view.HomeScreenView
import java.util.*

class HomeScreenPresenter(private val homeScreenView: HomeScreenView) :
    TimePicker.TimePickerListener {
    private val calendar = Calendar.getInstance()

    private val timePicker = TimePicker()
    private val timeRepository = TimeRepository()
    private val customAlarm = CustomAlarm()

    fun setAlarm(context: Context?) {
        if (context != null) {
            customAlarm.setAlarm(context, calendar.timeInMillis)
            timeRepository.saveTime(calendar, context)
            homeScreenView.showToast(context)
        }

    }

    fun stopAlarm(context: Context?) {
        if (context != null) {
            customAlarm.cancelAlarm(context)
            homeScreenView.hideToast(context)
        }

    }

    fun onTimeClicked() {
        homeScreenView.setNotClickableIv()
        homeScreenView.showTimePicker()
    }

    fun showTimePicker(fragmentManager: FragmentManager) {
        timePicker.showTimePicker(fragmentManager, this)
    }

    override fun positive(hours: Int, minutes: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hours)
        calendar.set(Calendar.MINUTE, minutes)
        calendar.set(Calendar.SECOND, 0)

        homeScreenView.setTimeText(TimeRefactor.refactor("$hours:$minutes"))
    }

    override fun negative() {
        homeScreenView.setClickableIv()

    }

    override fun dismiss() {
        homeScreenView.setClickableIv()

    }

}
