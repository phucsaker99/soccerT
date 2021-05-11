package com.example.soccert.utils

import android.content.Context
import es.dmoral.toasty.Toasty

const val Toast_LENGTH_DEFAULT = 1000

fun Context.showToast(type: ToastType, msg: String, duration: Int = Toast_LENGTH_DEFAULT) {
    when (type) {
        ToastType.Normal -> Toasty.normal(this, msg, duration).show()
        ToastType.Success -> Toasty.success(this, msg, duration).show()
        ToastType.Warning -> Toasty.warning(this, msg, duration).show()
        ToastType.Error -> Toasty.error(this, msg, duration).show()
    }
}
