package com.example.soccert.ui.home

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.databinding.FragmentHomeBinding
import com.example.soccert.ui.adapter.HomeViewPagerAdapter
import com.example.soccert.ui.adapter.LeagueAdapter
import com.example.soccert.ui.home.matchevent.MatchEventFragment
import com.example.soccert.ui.home.standing.StandingFragment
import com.example.soccert.ui.home.teams.TeamsFragment
import com.example.soccert.utils.loadImageRectangle
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val matchEventFragment = MatchEventFragment()
    private val standingFragment = StandingFragment()
    private val teamsFragment = TeamsFragment()
    private val leagueAdapter by lazy { context?.let { LeagueAdapter(it) } }
    private val args: HomeFragmentArgs by navArgs()

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

        spinnerLeagues.adapter = leagueAdapter
    }

    private fun setTabsWithViewPager() {
        binding.tabHome.setupWithViewPager(viewPagerHome)
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeVM = viewModel
            spinnerLeagues.adapter = leagueAdapter
        }

        checkArgs()
    }

    private fun checkArgs() {
        if (args.countryID == ARGS_DEFAULT) {
            viewModel.getInfoSoccer()
        } else {
            viewModel.setCountryIdAndReload(args.countryID)
        }
    }

    override fun initActions() {
        binding.imageCountry.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToRegionFragment()
            findNavController().navigate(action)
        }

        binding.spinnerLeagues.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.competition.observe(viewLifecycleOwner, Observer {
                        imageCountry.loadImageRectangle(it[position].countryLogo)
                        viewModel.setItemCompetition(it[position])
                    })
                }
            }
    }

    companion object {
        const val ARGS_DEFAULT = "-1"
    }
}
