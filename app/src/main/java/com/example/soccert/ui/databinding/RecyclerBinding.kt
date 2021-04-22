package com.example.soccert.ui.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soccert.base.BindDataAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("app:data")
fun <T> setDataRecyclerView(recyclerView: RecyclerView, data: List<T>?) {
    if (recyclerView.adapter is BindDataAdapter<*>) {
        (recyclerView.adapter as BindDataAdapter<T>).setData(data)
    }
}
