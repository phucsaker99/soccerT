package com.example.soccert.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccert.base.RxViewModel
import com.example.soccert.data.repository.AppPreferencesRepository

class MainViewModel(
    private val appPreferencesRepository: AppPreferencesRepository
) : RxViewModel() {

    private val _language = MutableLiveData<String>()
    val language: LiveData<String> get() = _language

    private val _languageChange = MutableLiveData<String>()
    val languageChange: LiveData<String> get() = _languageChange

    init {
        getLanguage()
    }

    private fun getLanguage() {
        _language.value = appPreferencesRepository.getLanguage()
    }

    fun setLanguage(language: String){
        appPreferencesRepository.setLanguage(language)
        _languageChange.value = appPreferencesRepository.getLanguage()
    }
}
