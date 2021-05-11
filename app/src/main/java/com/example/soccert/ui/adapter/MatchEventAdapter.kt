package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseAnimationAdapter
import com.example.soccert.base.BaseAnimationViewHolder
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.Event
import com.example.soccert.databinding.ItemMatchEventBinding
import com.example.soccert.utils.increaseHitArea

class MatchEventAdapter(
    private val onItemNotificationEvent: (Event) -> Unit,
    private val onItemClicked: (Event) -> Unit
) : BaseAdapter<Event, MatchEventAdapter.MatchEventViewHolder>(Event.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MatchEventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_match_event, parent, false
            ),
            onItemNotificationEvent,
            onItemClicked
        )

    fun notify(matchId: String) {
        val pos = currentList.indexOfFirst { it.matchID == matchId }
        notifyItemChanged(pos)
    }

    class MatchEventViewHolder(
        private val itemMatchEventBinding: ItemMatchEventBinding,
        onItemNotificationEvent: (Event) -> Unit,
        onItemClicked: (Event) -> Unit
    ) : BaseViewHolder<Event>(
        itemMatchEventBinding,
        onItemClicked
    ) {

        init {
            itemMatchEventBinding.imageEventNotification.apply {
                increaseHitArea(20f)
                setOnClickListener {
                    itemData?.let(onItemNotificationEvent)
                }
            }
        }

        override fun onBind(itemData: Event) {
            super.onBind(itemData)
            itemMatchEventBinding.event = itemData
        }
    }
}
