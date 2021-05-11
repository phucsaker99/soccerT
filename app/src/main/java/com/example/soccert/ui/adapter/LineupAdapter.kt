package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.*
import com.example.soccert.databinding.ItemLineupBinding

class LineupAdapter(
    private val onItemClicked: (Event) -> Unit
) : BaseAdapter<Event, LineupAdapter.EventViewHolder>(Event.diffUtil) {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_lineup, parent, false
            ),
            viewPool,
            onItemClicked
        )

    class EventViewHolder(
        private val itemLineupBinding: ItemLineupBinding,
        private val viewPool: RecyclerView.RecycledViewPool,
        onItemClicked: (Event) -> Unit
    ) : BaseViewHolder<Event>(itemLineupBinding, onItemClicked) {

        private val startingLineupAdapter = StartingLineupAdapter(this::itemClickedStartingLineup)
        private val substituteAdapter = SubstituteAdapter(this::itemClickedSubstitute)
        private val coachAdapter = CoachAdapter(this::itemClickedCoach)

        override fun onBind(itemData: Event) {
            super.onBind(itemData)
            itemLineupBinding.apply {
                recyclerStartingLineups.setRecycledViewPool(viewPool)
                recyclerSubstitutes.setRecycledViewPool(viewPool)
                recyclerCoaches.setRecycledViewPool(viewPool)

                recyclerStartingLineups.adapter = startingLineupAdapter
                recyclerSubstitutes.adapter = substituteAdapter
                recyclerCoaches.adapter = coachAdapter
            }

            if (adapterPosition == 0) {
                itemLineupBinding.apply {
                    itemData.lineup?.let {
                        startingLineups = it.home.startingLineups
                        substitutes = it.home.substitutes
                        coaches = it.home.coach
                    }
                }
            } else {
                itemLineupBinding.apply {
                    itemData.lineup?.let {
                        startingLineups = it.away.starting_lineups
                        substitutes = it.away.substitutes
                        coaches = it.away.coach
                    }
                }
            }

        }

        private fun itemClickedStartingLineup(startingLineup: StartingLineup) {}

        private fun itemClickedSubstitute(substitute: Substitute) {}

        private fun itemClickedCoach(coach: Coach) {}
    }
}
