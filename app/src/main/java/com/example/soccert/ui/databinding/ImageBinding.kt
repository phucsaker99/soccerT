package com.example.soccert.ui.databinding

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.soccert.R
import com.example.soccert.utils.hide
import com.example.soccert.utils.loadImageCircle
import com.example.soccert.utils.loadImageRectangle
import com.example.soccert.utils.show

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
fun ImageButton.loadStatusNotification(status: String) {
    if (status.isNotEmpty()) {
        hide()
        isEnabled = false
    }else{
        show()
        isEnabled = true
    }
}

@BindingAdapter("app:visibility")
fun View.checkImage(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("app:checkNotification")
fun ImageButton.checkNotification(value: Boolean) {
    if (value) setImageResource(R.drawable.ic_notification) else setImageResource(R.drawable.ic_no_notification)
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("app:notificationDate")
fun ImageButton.dateEvent(status: String) {
    if (status.isNotEmpty()) {
        hide()
        isEnabled = false
    } else show()
}
