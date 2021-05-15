package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.Event
import com.example.soccert.databinding.ItemNotificationBinding
import com.example.soccert.utils.increaseHitArea

class NotificationEventAdapter(
    private val onItemNotificationEvent: (Event) -> Unit,
    private val onItemClicked: (Event) -> Unit
) : BaseAdapter<Event, NotificationEventAdapter.NotificationEventViewHolder>(Event.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NotificationEventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_notification, parent, false
            ),
            onItemNotificationEvent,
            onItemClicked
        )

    fun notify(matchId : String){
        val pos = currentList.indexOfFirst { it.matchID == matchId }
        notifyItemChanged(pos)
    }

    class NotificationEventViewHolder(
        private val itemNotificationBinding: ItemNotificationBinding,
        onItemNotificationEvent: (Event) -> Unit,
        onItemClicked: (Event) -> Unit
    ) : BaseViewHolder<Event>(itemNotificationBinding, onItemClicked) {

        init {
            itemNotificationBinding.imageNotification.apply {
                increaseHitArea(20f)
                setOnClickListener {
                    itemData?.let(onItemNotificationEvent)
                }
            }
        }

        override fun onBind(itemData: Event) {
            super.onBind(itemData)
            itemNotificationBinding.event = itemData
        }
    }
}
