package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.Player
import com.example.soccert.databinding.ItemInfoPlayerBinding

class PlayerAdapter(
    private val onItemClicked: (Player) -> Unit
) : BaseAdapter<Player, PlayerAdapter.EventViewHolder>(Player.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_info_player, parent, false
            ),
            onItemClicked
        )

    class EventViewHolder(
        private val itemInfoPlayerBinding: ItemInfoPlayerBinding,
        onItemClicked: (Player) -> Unit
    ) : BaseViewHolder<Player>(itemInfoPlayerBinding, onItemClicked) {

        override fun onBind(itemData: Player) {
            super.onBind(itemData)
            itemInfoPlayerBinding.player = itemData
        }
    }
}
