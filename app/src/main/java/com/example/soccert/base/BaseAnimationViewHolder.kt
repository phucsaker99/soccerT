package com.example.soccert.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAnimationViewHolder<V : ViewDataBinding, T>(
    binding: V,
    onItemClick: (V, T) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    protected var itemData: T? = null

    init {
        binding.root.setOnClickListener {
            itemData?.let { onItemClick(binding, it) }
        }
    }

    open fun onBind(itemData: T) {
        this.itemData = itemData
    }
}
