package com.example.soccert.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.soccert.R

fun ImageView.loadImageCircle(urlImage: String) {
    Glide.with(context)
        .load(urlImage)
        .circleCrop()
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.image_errror)
        .into(this)
}

fun ImageView.loadImageRectangle(urlImage: String) {
    Glide.with(context)
        .load(urlImage)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.image_errror)
        .into(this)
}
