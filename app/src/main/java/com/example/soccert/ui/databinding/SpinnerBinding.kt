package com.example.soccert.ui.databinding

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.example.soccert.base.BindDataAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("app:data_spinner")
fun <T> setDataSpinnerView(spinner: Spinner, data: List<T>?) {
    if (spinner.adapter is BindDataAdapter<*>) {
        (spinner.adapter as BindDataAdapter<T>).setData(data)
    }
}
