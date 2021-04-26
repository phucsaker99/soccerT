package com.example.soccert.ui.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.soccert.utils.hide
import com.example.soccert.utils.loadImageCircle
import com.example.soccert.utils.loadImageRectangle

@BindingAdapter("app:image", "app:cropCircle", requireAll = false)
fun ImageView.loadUrlImageCircle(urlImage: String?, cropCircle: Boolean = false) {
    if (!urlImage.isNullOrEmpty()) {
        if (cropCircle) {
            loadImageCircle(urlImage)
        } else {
            loadImageRectangle(urlImage)
        }
    } else {
        setImageDrawable(null)
    }
}

@BindingAdapter("app:notificationStatus")
fun ImageView.loadStatusNotification(status: String) {
    if (status.isNotEmpty()) hide()
}
