package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.Standing
import com.example.soccert.databinding.ItemInfoStandingBinding

class StandingAdapter(
    private val onItemClicked: (Standing) -> Unit
) : BaseAdapter<Standing, StandingAdapter.StandingViewHolder>(Standing.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StandingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_info_standing, parent, false
            ),
            onItemClicked
        )

    class StandingViewHolder(
        private val itemInfoStandingBinding: ItemInfoStandingBinding,
        onItemClicked: (Standing) -> Unit
    ) : BaseViewHolder<Standing>(itemInfoStandingBinding, onItemClicked) {

        override fun onBind(itemData: Standing) {
            super.onBind(itemData)
            itemInfoStandingBinding.standing = itemData
        }
    }
}
