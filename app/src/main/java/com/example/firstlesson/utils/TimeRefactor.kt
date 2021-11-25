package com.example.firstlesson.utils

object TimeRefactor {
    // типо можно было использовать SimpleDateFormat))0)0))
    fun refactor(time: String): String {
        var hour = time.split(":")[0]
        var min = time.split(":")[1]
        if (hour.length < 2) {
            hour = "${0}$hour"

        }
        if (min.length < 2) {
            min = "${0}$min"

        }
        return "$hour:$min"

    }
}
