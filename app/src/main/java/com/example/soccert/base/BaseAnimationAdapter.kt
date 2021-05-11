package com.example.soccert.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAnimationAdapter<T, V : ViewDataBinding, VH : BaseAnimationViewHolder<V, T>>(
    diffUtilItem: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffUtilItem), BindDataAdapter<T> {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun setData(data: List<T>?) {
        submitList(data)
    }
}
