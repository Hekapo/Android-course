package com.example.firstlesson.models

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Song(
    val id: Int,
    @DrawableRes val cover: Int,
    @RawRes val audio: Int,
    val audioTitle: String,
    var state: State = State.PAUSE
)

enum class State {
    PAUSE, PLAY, STOP
}
