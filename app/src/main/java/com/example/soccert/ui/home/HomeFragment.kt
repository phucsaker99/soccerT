package com.example.soccert.ui.home

import androidx.navigation.fragment.findNavController
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.databinding.FragmentHomeBinding
import com.example.soccert.ui.adapter.HomeViewPagerAdapter
import com.example.soccert.ui.adapter.LeagueAdapter
import com.example.soccert.ui.home.matchevent.MatchEventFragment
import com.example.soccert.ui.home.standing.StandingFragment
import com.example.soccert.ui.home.teams.TeamsFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val matchEventFragment = MatchEventFragment()
    private val standingFragment = StandingFragment()
    private val teamsFragment = TeamsFragment()
    private val leagueAdapter by lazy { context?.let { LeagueAdapter(it) } }

    override val layoutResource get() = R.layout.fragment_home
    override val viewModel by sharedViewModel<HomeViewModel>()

    override fun initViews() {
        setAdapter()
        setTabsWithViewPager()
    }

    private fun setAdapter() {
        HomeViewPagerAdapter(childFragmentManager).apply {
            addFragment(matchEventFragment, getString(R.string.title_match_events))
            addFragment(standingFragment, getString(R.string.title_standing))
            addFragment(teamsFragment, getString(R.string.title_team))
            viewPagerHome.adapter = this
        }
    }

    private fun setTabsWithViewPager() {
        tabHome.setupWithViewPager(viewPagerHome)
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeVM = viewModel
        }

        spinnerLeagues.adapter = leagueAdapter
    }

    override fun initActions() {
        imageCountry.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToRegionFragment()
            findNavController().navigate(action)
        }
    }
}
