package com.example.soccert.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    binding: ViewDataBinding,
    onItemClick: (T) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    protected var itemData: T? = null

    init {
        binding.root.setOnClickListener {
            itemData?.let { onItemClick(it) }
        }
    }

    open fun onBind(itemData: T) {
        this.itemData = itemData
    }
}
