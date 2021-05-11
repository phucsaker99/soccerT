package com.example.soccert.ui.detailteam

import android.transition.TransitionInflater
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.data.model.Player
import com.example.soccert.databinding.FragmentDetailTeamBinding
import com.example.soccert.ui.adapter.PlayerAdapter
import kotlinx.android.synthetic.main.fragment_detail_team.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTeamFragment : BaseFragment<FragmentDetailTeamBinding>() {
    private val args: DetailTeamFragmentArgs by navArgs()
    private val playerGoalkeepersAdapter = PlayerAdapter(this::itemSelectedPlayer)
    private val playerDefendersAdapter = PlayerAdapter(this::itemSelectedPlayer)
    private val playerMidfieldersAdapter = PlayerAdapter(this::itemSelectedPlayer)
    private val playerForwardsAdapter = PlayerAdapter(this::itemSelectedPlayer)

    override val layoutResource get() = R.layout.fragment_detail_team
    override val viewModel by viewModel<DetailTeamViewModel>()

    override fun initViews() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            recyclerGoalkeepers.adapter = playerGoalkeepersAdapter
            recyclerDefenders.adapter = playerDefendersAdapter
            recyclerMidfielders.adapter = playerMidfieldersAdapter
            recyclerForwards.adapter = playerForwardsAdapter
            teamVM = viewModel
        }
        viewModel.getInitTeam(args.team)
    }

    override fun initActions() {
        imageBack.setOnClickListener { findNavController().popBackStack() }

        imageFavorite.setOnClickListener {
            viewModel.updateFavoriteTeam()
        }
    }

    private fun itemSelectedPlayer(player: Player) {}
}
