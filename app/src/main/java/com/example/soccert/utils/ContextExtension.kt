package com.example.soccert.utils

import android.content.Context
import android.widget.Toast
import es.dmoral.toasty.Toasty

fun Context.showToast(type: ToastType, msg: String, duration: Int = Toast.LENGTH_SHORT) {
    when (type) {
        ToastType.Normal -> Toasty.normal(this, msg, duration)
        ToastType.Success -> Toasty.success(this, msg, duration)
        ToastType.Warning -> Toasty.warning(this, msg, duration)
        ToastType.Error -> Toasty.error(this, msg, duration)
    }
}
