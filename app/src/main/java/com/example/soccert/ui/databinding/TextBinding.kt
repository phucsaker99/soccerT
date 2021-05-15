package com.example.soccert.ui.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.soccert.utils.hide
import com.example.soccert.utils.show

@BindingAdapter("app:textCheckContent")
fun TextView.checkContent(content: String?) {
    if (content.isNullOrEmpty()) {
        hide()
    } else {
        show()
        text = content
    }
}

