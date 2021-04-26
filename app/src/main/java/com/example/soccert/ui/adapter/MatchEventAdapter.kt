package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.Competition
import com.example.soccert.data.model.Event
import com.example.soccert.databinding.ItemMatchEventBinding

class MatchEventAdapter(
    private val onItemClicked: (Event) -> Unit
) : BaseAdapter<Event, MatchEventAdapter.MatchEventViewHolder>(Event.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MatchEventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_match_event, parent, false
            ),
            onItemClicked
        )

    class MatchEventViewHolder(
        private val itemMatchEventBinding: ItemMatchEventBinding,
        onItemClicked: (Event) -> Unit
    ) : BaseViewHolder<Event>(itemMatchEventBinding, onItemClicked) {

        override fun onBind(itemData: Event) {
            super.onBind(itemData)
            itemMatchEventBinding.event = itemData
        }
    }
}
