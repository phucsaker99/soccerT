package com.example.soccert.ui.databinding

import android.view.MotionEvent
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soccert.base.BindDataAdapter
import com.example.soccert.data.model.Player

@Suppress("UNCHECKED_CAST")
@BindingAdapter("app:data")
fun <T> RecyclerView.setDataRecyclerView(data: List<T>?) {
    if (adapter is BindDataAdapter<*>) {
        (adapter as BindDataAdapter<T>).setData(data)
    }
    startLayoutAnimation()
}

@BindingAdapter("app:dataFilter", "app:playerType")
fun RecyclerView.setDataPlayerByType(data: List<Player>, type: String) =
    setDataRecyclerView(data.filter { item -> item.playerType == type })

@BindingAdapter("app:touchState")
fun RecyclerView.setTouchListener(type: Boolean) {
    addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent) = type
    })
}
