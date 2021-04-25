package com.example.soccert.ui.home.matchevent

import android.app.DatePickerDialog
import com.example.soccert.R
import androidx.lifecycle.Observer
import com.example.soccert.base.BaseFragment
import com.example.soccert.data.model.Event
import com.example.soccert.databinding.FragmentMatchEventBinding
import com.example.soccert.ui.adapter.MatchEventAdapter
import com.example.soccert.ui.home.HomeViewModel
import com.example.soccert.utils.ToastType
import com.example.soccert.utils.showToast
import kotlinx.android.synthetic.main.fragment_match_event.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.time.LocalDate
import java.util.*

class MatchEventFragment : BaseFragment<FragmentMatchEventBinding>() {
    private val calendar = Calendar.getInstance()
    private val adapterEvent = MatchEventAdapter(this::itemSelectedEvent)

    override val layoutResource get() = R.layout.fragment_match_event
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
        binding.imageFromDate.setOnClickListener {
            showDialogFromDate()
        }

        binding.imageToDate.setOnClickListener {
            showDialogToDate()
        }

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

    private fun showDialogFromDate() {
        context?.let {
            DatePickerDialog(
                it,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    textFromDate.text = resources.getString(
                        R.string.text_date_holder, year, month + 1, dayOfMonth
                    )
                    viewModel.getEventByDateAndLeague(
                        textFromDate.text.toString(),
                        textToDate.text.toString()
                    )
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun showDialogToDate() {
        context?.let {
            DatePickerDialog(
                it,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    textToDate.text = String.format(
                        resources.getString(R.string.text_date_holder),
                        year,
                        month + 1,
                        dayOfMonth
                    )
                    viewModel.getEventByDateAndLeague(
                        textFromDate.text.toString(),
                        textToDate.text.toString()
                    )
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun itemSelectedEvent(event: Event) {

    }
}
