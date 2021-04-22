package com.example.soccert.ui.home.matchevent

import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.databinding.FragmentHomeBinding
import com.example.soccert.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MatchEventFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutResource get() = R.layout.fragment_match_event
    override val viewModel by sharedViewModel<HomeViewModel>()

    override fun initViews() {

    }

    override fun initData() {

    }

    override fun initActions() {

    }
}
