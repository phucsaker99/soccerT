package com.example.soccert.di

import com.example.soccert.ui.detailmatch.DetailMatchViewModel
import com.example.soccert.ui.detailteam.DetailTeamViewModel
import com.example.soccert.ui.favorite.FavoriteTeamViewModel
import com.example.soccert.ui.home.HomeViewModel
import com.example.soccert.ui.main.MainViewModel
import com.example.soccert.ui.news.NewsViewModel
import com.example.soccert.ui.notification.NotificationViewModel
import com.example.soccert.ui.region.RegionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegionViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { DetailMatchViewModel(get()) }
    viewModel { DetailTeamViewModel(get()) }
    viewModel { FavoriteTeamViewModel(get()) }
    viewModel { NotificationViewModel(get()) }
    viewModel { MainViewModel(get()) }
}
