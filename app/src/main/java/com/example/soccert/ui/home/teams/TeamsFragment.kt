package com.example.soccert.ui.home.teams

import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.data.model.Team
import com.example.soccert.databinding.FragmentTeamsBinding
import com.example.soccert.databinding.ItemTeamBinding
import com.example.soccert.ui.adapter.TeamAdapter
import com.example.soccert.ui.home.HomeFragmentDirections
import com.example.soccert.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TeamsFragment : BaseFragment<FragmentTeamsBinding>() {
    private val teamAdapter = TeamAdapter(this::setSelectedTeam)

    override val layoutResource get() = R.layout.fragment_teams
    override val viewModel by sharedViewModel<HomeViewModel>()

    override fun initViews() {}

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            teamVM = viewModel
            recyclerTeams.adapter = teamAdapter
        }
    }

    override fun initActions() {}

    private fun setSelectedTeam(itemTeamBinding: ItemTeamBinding, team: Team) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailTeamFragment(team)
        findNavController().navigate(
            action,
            FragmentNavigatorExtras(
                itemTeamBinding.imageTeam to team.teamBadge,
                itemTeamBinding.textTeamName to team.teamName
            )
        )
    }
}
