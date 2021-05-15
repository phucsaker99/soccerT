package com.example.soccert.ui.detailmatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccert.base.RxViewModel
import com.example.soccert.data.model.Event
import com.example.soccert.data.model.Team
import com.example.soccert.data.repository.SoccerRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailMatchViewModel(
    private val soccerRepository: SoccerRepository
) : RxViewModel() {

    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event> get() = _event

    private val _teamHome = MutableLiveData<Team>()
    val teamHome: LiveData<Team> get() = _teamHome

    private val _teamAway = MutableLiveData<Team>()
    val teamAway: LiveData<Team> get() = _teamAway

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    fun getEvent(eventID: Int) {
        _isLoading.value != _event.value?.let { it.matchID != eventID.toString() }

        soccerRepository.getMatch(eventID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (_event.value == null || _event.value != it[0]) {
                    _event.value = it[0]
                    _isLoading.value = false
                }
            }, {
                _error.value = it.message.toString()
                _isLoading.value = false
            }).addTo(disposables)
    }

    fun getTeams(event: Event) {
        _isLoading.value = true
        soccerRepository.getTeam(event.matchHomeTeamID.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _teamHome.value = it[0]
                _isLoading.value = false
            }, {
                _error.value = it.message.toString()
                _isLoading.value = false
            })
            .addTo(disposables)

        soccerRepository.getTeam(event.matchAwayTeamID.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _teamAway.value = it[0]
                _isLoading.value = false
            }, {
                _error.value = it.message.toString()
                _isLoading.value = false
            })
            .addTo(disposables)
    }
}
