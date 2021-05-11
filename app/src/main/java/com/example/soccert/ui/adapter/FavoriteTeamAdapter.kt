package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.soccert.R
import com.example.soccert.base.BaseAnimationAdapter
import com.example.soccert.base.BaseAnimationViewHolder
import com.example.soccert.data.model.Team
import com.example.soccert.databinding.ItemFavoriteTeamBinding

class FavoriteTeamAdapter(
    private val onItemClicked: (ItemFavoriteTeamBinding, Team) -> Unit
) : BaseAnimationAdapter<Team, ItemFavoriteTeamBinding, FavoriteTeamAdapter.FavoriteViewHolder>(Team.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_favorite_team, parent, false
            ),
            onItemClicked
        )

    class FavoriteViewHolder(
        private val itemFavoriteTeamBinding: ItemFavoriteTeamBinding,
        onItemClicked: (ItemFavoriteTeamBinding, Team) -> Unit
    ) : BaseAnimationViewHolder<ItemFavoriteTeamBinding, Team>(
        itemFavoriteTeamBinding,
        onItemClicked
    ) {

        override fun onBind(itemData: Team) {
            super.onBind(itemData)
            itemFavoriteTeamBinding.team = itemData
        }
    }
}
