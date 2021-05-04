package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.Team
import com.example.soccert.databinding.ItemTeamBinding

class TeamAdapter(
    private val onItemClicked: (Team) -> Unit
) : BaseAdapter<Team, TeamAdapter.EventViewHolder>(Team.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_team, parent, false
            ),
            onItemClicked
        )

    class EventViewHolder(
        private val itemTeamBinding: ItemTeamBinding,
        onItemClicked: (Team) -> Unit
    ) : BaseViewHolder<Team>(itemTeamBinding, onItemClicked) {

        override fun onBind(itemData: Team) {
            super.onBind(itemData)
            itemTeamBinding.team = itemData
        }
    }
}
