package com.example.soccert.ui.home

import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutResource get() = R.layout.fragment_home

    override val viewModel by viewModel<HomeViewModel>()

    override fun initViews() {

    }

    override fun initData() {

    }

    override fun initActions() {

    }
}
