package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.Statistic
import com.example.soccert.databinding.ItemStatisticBinding

class StatisticAdapter(
    private val onItemClicked: (Statistic) -> Unit
) : BaseAdapter<Statistic, StatisticAdapter.EventViewHolder>(Statistic.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_statistic, parent, false
            ),
            onItemClicked
        )

    class EventViewHolder(
        private val itemStatisticBinding: ItemStatisticBinding,
        onItemClicked: (Statistic) -> Unit
    ) : BaseViewHolder<Statistic>(itemStatisticBinding, onItemClicked) {

        override fun onBind(itemData: Statistic) {
            super.onBind(itemData)
            itemStatisticBinding.statistic = itemData
        }
    }
}
