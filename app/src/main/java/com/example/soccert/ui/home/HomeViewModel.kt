package com.example.soccert.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccert.base.RxViewModel
import com.example.soccert.data.model.Competition
import com.example.soccert.data.model.Event
import com.example.soccert.data.repository.AppPreferencesRepository
import com.example.soccert.data.repository.SoccerRepository
import com.example.soccert.utils.PopularLeaguesUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(
    private val soccerRepository: SoccerRepository,
    private val appPreferencesRepository: AppPreferencesRepository
) : RxViewModel() {

    private val _competitionType = MutableLiveData<String>()
    val competitionType: LiveData<String> get() = _competitionType

    private val _competition = MutableLiveData<List<Competition>>()
    val competition: LiveData<List<Competition>> get() = _competition

    private val _event = MutableLiveData<List<Event>>()
    val event: LiveData<List<Event>> get() = _event

    private val _itemCompetition = MutableLiveData<Competition>()
    val itemCompetition: LiveData<Competition> get() = _itemCompetition

    fun getInfoSoccer() {
        getCompetitionType()
        getLeagues()
    }

    fun setCountryIdAndReload(countryID: String) {
        appPreferencesRepository.setCountryID(countryID)
        getInfoSoccer()
    }

    fun setItemCompetition(competition: Competition) {
        _itemCompetition.value = competition
    }

    fun getEventByDateAndLeague(fromDate: String, toDate: String) {
        _itemCompetition.value?.let { item ->
            soccerRepository.getEvents(item.leagueID, fromDate, toDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _event.value = it
                }, {
                    _event.value = emptyList()
                    _error.value = it.message.toString()
                }).addTo(disposables)
        }
    }

    private fun getCompetitionType() {
        _competitionType.value = appPreferencesRepository.getCountryID()
    }

    private fun getLeagues() {
        if (competitionType.value.isNullOrEmpty()) {
            getPopularLeagues()
        } else {
            getLeaguesByCountry(competitionType.value.toString())
        }
    }

    private fun getLeaguesByCountry(countryID: String) {
        soccerRepository.getLeagues(countryID.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _competition.value = it
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }

    private fun getPopularLeagues() {
        _competition.value = PopularLeaguesUtil.popularLeagues
    }
}
