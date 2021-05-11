package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.StartingLineup
import com.example.soccert.databinding.ItemPlayerBinding

class StartingLineupAdapter(
    private val onItemClicked: (StartingLineup) -> Unit
) : BaseAdapter<StartingLineup, StartingLineupAdapter.EventViewHolder>(StartingLineup.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_player, parent, false
            ),
            onItemClicked
        )

    class EventViewHolder(
        private val itemPlayerBinding: ItemPlayerBinding,
        onItemClicked: (StartingLineup) -> Unit
    ) : BaseViewHolder<StartingLineup>(itemPlayerBinding, onItemClicked) {

        override fun onBind(itemData: StartingLineup) {
            super.onBind(itemData)
            itemPlayerBinding.apply {
                position = itemData.lineupNumber
                playerName = itemData.lineupPlayer
            }
        }
    }
}
