package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.Country
import com.example.soccert.databinding.ItemCountryBinding

class CountryAdapter(
    private val onItemClicked: (Country) -> Unit
) : BaseAdapter<Country, CountryAdapter.EventViewHolder>(Country.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_country, parent, false
            ),
            onItemClicked
        )

    class EventViewHolder(
        private val itemCountryBinding: ItemCountryBinding,
        onItemClicked: (Country) -> Unit
    ) : BaseViewHolder<Country>(itemCountryBinding, onItemClicked) {

        override fun onBind(itemData: Country) {
            super.onBind(itemData)
            itemCountryBinding.country = itemData
        }
    }
}
