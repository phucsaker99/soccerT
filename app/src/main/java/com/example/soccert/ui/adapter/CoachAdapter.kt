package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.Coach
import com.example.soccert.databinding.ItemPlayerBinding
import com.example.soccert.utils.show

class CoachAdapter(
    private val onItemClicked: (Coach) -> Unit
) : BaseAdapter<Coach, CoachAdapter.EventViewHolder>(Coach.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_player, parent, false
            ),
            onItemClicked
        )

    class EventViewHolder(
        private val itemPlayerBinding: ItemPlayerBinding,
        onItemClicked: (Coach) -> Unit
    ) : BaseViewHolder<Coach>(itemPlayerBinding, onItemClicked) {

        override fun onBind(itemData: Coach) {
            super.onBind(itemData)
            itemPlayerBinding.apply {
                imageCoach.show()
                playerName = itemData.lineupPlayer
            }
        }
    }
}
