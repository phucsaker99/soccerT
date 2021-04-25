package com.example.soccert.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BindDataAdapter
import com.example.soccert.data.model.Competition
import com.example.soccert.databinding.ItemLeagueBinding
import com.example.soccert.databinding.ItemLeagueSelectedBinding

class LeagueAdapter(
    context: Context
) : ArrayAdapter<Competition>(context, 0), BindDataAdapter<Competition> {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = DataBindingUtil.inflate<ItemLeagueSelectedBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_league_selected,
            parent,
            false
        )
        binding.competition = getItem(position)
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = DataBindingUtil.inflate<ItemLeagueBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_league,
            parent,
            false
        )
        binding.competition = getItem(position)
        return binding.root
    }

    override fun setData(data: List<Competition>?) {
        data?.let {
            clear()
            addAll(it.toMutableList())
        }
    }
}
