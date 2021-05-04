package com.example.soccert.ui.detailmatch

import androidx.navigation.fragment.navArgs
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.data.model.Event
import com.example.soccert.data.model.Statistic
import com.example.soccert.databinding.FragmentDetailMatchBinding
import com.example.soccert.ui.adapter.StatisticAdapter
import com.example.soccert.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailMatchFragment : BaseFragment<FragmentDetailMatchBinding>() {
    private val args: DetailMatchFragmentArgs by navArgs()
    private val statisticAdapter = StatisticAdapter(this::itemClickedStatistic)

    override val layoutResource get() = R.layout.fragment_detail_match
    override val viewModel by sharedViewModel<HomeViewModel>()

    override fun initViews() {
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            recyclerStatistics.adapter = statisticAdapter
            event = args.event
        }
    }

    override fun initActions() {

    }

    private fun itemClickedStatistic(statistic: Statistic) {

    }
}
