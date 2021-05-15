package com.example.soccert.ui.home.matchevent

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.view.View
import com.example.soccert.R
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.soccert.base.BaseFragment
import com.example.soccert.data.model.Event
import com.example.soccert.databinding.FragmentMatchEventBinding
import com.example.soccert.ui.adapter.MatchEventAdapter
import com.example.soccert.ui.home.HomeFragmentDirections
import com.example.soccert.ui.home.HomeViewModel
import com.example.soccert.utils.AlarmManagerUtil
import com.example.soccert.utils.ToastType
import com.example.soccert.utils.TranslateAnimationUtil
import com.example.soccert.utils.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_match_event.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MatchEventFragment : BaseFragment<FragmentMatchEventBinding>() {
    private val calendar = Calendar.getInstance()
    private val adapterEvent =
        MatchEventAdapter(
            this::itemSelectedNotification,
            this::itemSelectedEvent
        )

    override val layoutResource get() = R.layout.fragment_match_event
    override val viewModel by sharedViewModel<HomeViewModel>()

    override fun initViews() {
        initEventDate()
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeVM = viewModel
            recyclerEvent.adapter = adapterEvent
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

        viewModel.isNotify.observe(this, Observer {
            adapterEvent.notify(viewModel.matchID)
        })

        viewModel.deleteEvent.observe(this, Observer {
            deleteEventNotification(it)
        })

        viewModel.addEvent.observe(this, Observer {
            addEventNotification(it)
        })
    }

    private fun initEventDate() {
        binding.apply {
            textFromDate.text = LocalDate.now().minusDays(FROM_DATE_DEFAULT).toString()
            textToDate.text = LocalDate.now().plusDays(TO_DATE_DEFAULT).toString()
        }
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

    private fun addEventNotification(event: Event) {
        try {
            context?.let {
                AlarmManagerUtil.create(
                    it,
                    event.matchID,
                    System.currentTimeMillis()
                )
            }
        } catch (e: Exception) {
            context?.showToast(ToastType.Error, e.message.toString())
        }
    }

    private fun deleteEventNotification(event: Event) {
        context?.let { AlarmManagerUtil.cancel(it, event.matchID) }
    }

    private fun itemSelectedNotification(event: Event) {
        if (event.isNotification) {
            viewModel.deleteNotification(event)
        } else {
            viewModel.addNotification(event)
        }
    }

    private fun itemSelectedEvent(event: Event) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailMatchFragment(event.matchID.toInt())
        findNavController().navigate(action)
    }

    companion object {
        const val FROM_DATE_DEFAULT = 10L
        const val TO_DATE_DEFAULT = 10L
    }
}
