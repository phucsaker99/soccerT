package com.example.soccert.ui.detailmatch

import com.example.soccert.R
import androidx.lifecycle.Observer
import com.example.soccert.base.BaseFragment
import com.example.soccert.data.model.Event
import com.example.soccert.databinding.FragmentMatchEventBinding
import com.example.soccert.ui.adapter.MatchEventAdapter
import com.example.soccert.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_match_event.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.time.LocalDate

class DetailMatchFragment : BaseFragment<FragmentMatchEventBinding>() {
    private val adapterEvent = MatchEventAdapter(this::itemSelectedEvent)

    override val layoutResource get() = R.layout.fragment_detail_match
    override val viewModel by sharedViewModel<HomeViewModel>()

    override fun initViews() {
        initEventDate()
        initAdapter()
    }

    private fun initAdapter() {
        recyclerEvent.adapter = adapterEvent
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeVM = viewModel
        }
    }

    override fun initActions() {
        viewModel.itemCompetition.observe(
            viewLifecycleOwner, Observer {
                viewModel.getEventByDateAndLeague(
                    textFromDate.text.toString(),
                    textToDate.text.toString()
                )
            }
        )
    }

    private fun initEventDate() {
        textFromDate.text = LocalDate.now().minusDays(10).toString()
        textToDate.text = LocalDate.now().plusDays(10).toString()
        viewModel.getEventByDateAndLeague(textFromDate.text.toString(), textToDate.text.toString())
    }

    private fun itemSelectedEvent(event: Event) {

    }
}
