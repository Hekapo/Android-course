package com.example.firstlesson

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object LoadImage {

    fun load(imageUrl: String, imageView: ImageView) {
        Glide.with(imageView)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(imageView)
    }
}
