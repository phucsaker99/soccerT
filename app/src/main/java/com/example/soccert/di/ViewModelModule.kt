package com.example.soccert.di

import com.example.soccert.ui.home.HomeViewModel
import com.example.soccert.ui.region.RegionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegionViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}
