package com.example.soccert.ui.region

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccert.base.RxViewModel
import com.example.soccert.data.model.Country
import com.example.soccert.data.repository.SoccerRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class RegionViewModel(
    private val soccerRepository: SoccerRepository
) : RxViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getCountries()
    }

    private fun getCountries() {
        _isLoading.value = true
        soccerRepository.getCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _countries.value = it
                _isLoading.value = false
            }, {
                _error.value = it.message.toString()
                _isLoading.value = false
            })
            .addTo(disposables)
    }
}
