package com.example.soccert.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccert.base.RxViewModel
import com.example.soccert.data.model.Country
import com.example.soccert.data.repository.SoccerRepository

class HomeViewModel(
    private val soccerRepository: SoccerRepository
) : RxViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries
}
