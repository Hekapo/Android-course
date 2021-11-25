package com.example.firstlesson.time_picker

import androidx.fragment.app.FragmentManager
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class TimePicker {

    interface TimePickerListener {
        fun positive(hours: Int, minutes: Int)
        fun negative()
        fun dismiss()

    }

    fun showTimePicker(fragmentManager: FragmentManager, timePickerListener: TimePickerListener?) {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setTitleText("Set time")
            .build()

        picker.show(fragmentManager, "TAG")

        picker.addOnPositiveButtonClickListener {
            timePickerListener?.positive(picker.hour, picker.minute)

        }

        picker.addOnNegativeButtonClickListener {
            timePickerListener?.negative()
        }
        picker.addOnDismissListener {
            timePickerListener?.dismiss()
            it.cancel()
        }

    }

}


