package com.example.soccert.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.soccert.base.RxViewModel
import com.example.soccert.data.model.Competition
import com.example.soccert.data.model.Event
import com.example.soccert.data.model.Standing
import com.example.soccert.data.model.Team
import com.example.soccert.data.repository.AppPreferencesRepository
import com.example.soccert.data.repository.SoccerRepository
import com.example.soccert.utils.ExceptionUtil
import com.example.soccert.utils.PopularLeaguesUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

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

    private val _standing = MutableLiveData<List<Standing>>()
    val standing: LiveData<List<Standing>> get() = _standing

    private val _team = MutableLiveData<List<Team>>()
    val team: LiveData<List<Team>> get() = _team

    private val competitionSubject = BehaviorSubject.create<Competition>()
    private val competitionObserver = Observer<Competition> {
        competitionSubject.onNext(it)
    }

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

        soccerRepository.getStandings(competition.leagueID.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _standing.value = it
            }, {
                if (it.message.toString() == ExceptionUtil.EXCEPTION_RETURN_OBJECT) {
                    _standing.value = emptyList()
                } else {
                    _error.value = it.message.toString()
                }
            }).addTo(disposables)

        soccerRepository.getTeams(competition.leagueID.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _team.value = it
            }, {
                if (it.message.toString() == ExceptionUtil.EXCEPTION_RETURN_OBJECT) {
                    _team.value = emptyList()
                } else {
                    _error.value = it.message.toString()
                }
            }).addTo(disposables)
    }

    fun getEventByDateAndLeague(fromDate: String, toDate: String) {
        _itemCompetition.observeForever(competitionObserver)

        competitionSubject
            .subscribeOn(Schedulers.io())
            .switchMap {
                soccerRepository.getEvents(it.leagueID, fromDate, toDate)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _event.value = it
            }, {
                if (it.message.toString() == ExceptionUtil.EXCEPTION_RETURN_OBJECT) {
                    _event.value = emptyList()
                } else {
                    _error.value = it.message.toString()
                }
            })
            .addTo(disposables)
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

    override fun onCleared() {
        super.onCleared()
        _itemCompetition.removeObserver(competitionObserver)
    }
}
