package com.example.soccert.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.data.model.Team
import com.example.soccert.databinding.FragmentFavoriteBinding
import com.example.soccert.databinding.ItemFavoriteTeamBinding
import com.example.soccert.ui.adapter.FavoriteTeamAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    private val favoriteTeamAdapter = FavoriteTeamAdapter(this::itemSelectedTeam)

    override val layoutResource get() = R.layout.fragment_favorite
    override val viewModel by viewModel<FavoriteTeamViewModel>()

    override fun initViews() {
        initToolbar()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbarFavoriteTeam)
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            favoriteVM = viewModel
            recyclerFavoriteTeam.adapter = favoriteTeamAdapter
        }
    }

    override fun initActions() {
    }

    private fun itemSelectedTeam(itemFavoriteTeamBinding: ItemFavoriteTeamBinding, team: Team) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailTeamFragment(team)
        findNavController().navigate(
            action,
            FragmentNavigatorExtras(
                itemFavoriteTeamBinding.imageTeam to team.teamBadge,
                itemFavoriteTeamBinding.textTeamTitle to team.teamName
            )
        )
    }
}
