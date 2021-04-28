package com.example.soccert.ui.home.standing

import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.data.model.Standing
import com.example.soccert.databinding.FragmentStandingBinding
import com.example.soccert.ui.adapter.StandingAdapter
import com.example.soccert.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StandingFragment : BaseFragment<FragmentStandingBinding>() {
    private val adapterStanding = StandingAdapter(this::itemSelectedStanding)

    override val layoutResource get() = R.layout.fragment_standing
    override val viewModel by sharedViewModel<HomeViewModel>()

    override fun initViews() {
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            standingVM = viewModel
            recyclerStanding.adapter = adapterStanding
        }
    }

    override fun initActions() {

    }

    private fun itemSelectedStanding(standing: Standing) {

    }
}
