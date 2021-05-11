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
import com.example.soccert.utils.AlarmManagerUtil
import com.example.soccert.utils.ExceptionUtil
import com.example.soccert.utils.PopularLeaguesUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
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

    private val _deleteEvent = MutableLiveData<Event>()
    val deleteEvent: LiveData<Event> get() = _deleteEvent

    private val _addEvent = MutableLiveData<Event>()
    val addEvent: LiveData<Event> get() = _addEvent

    private val _itemCompetition = MutableLiveData<Competition>()
    val itemCompetition: LiveData<Competition> get() = _itemCompetition

    private val _standing = MutableLiveData<List<Standing>>()
    val standing: LiveData<List<Standing>> get() = _standing

    private val _team = MutableLiveData<List<Team>>()
    val team: LiveData<List<Team>> get() = _team

    var isNotify = MutableLiveData<Boolean>(false)
    var matchID = ""

    private val _notificationMatch = MutableLiveData<List<Event>>()
    val notificationMatch: LiveData<List<Event>> get() = _notificationMatch

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val competitionSubject = BehaviorSubject.create<Competition>()
    private val competitionObserver = Observer<Competition> {
        competitionSubject.onNext(it)
    }

    fun getInfoSoccer() {
        getCompetitionType()
        getLeagues()
        getEventNotification()
    }

    fun setCountryIdAndReload(countryID: String) {
        appPreferencesRepository.setCountryID(countryID)
        getInfoSoccer()
    }

    fun setItemCompetition(competition: Competition) {
        _isLoading.value = true

        _itemCompetition.value = competition
        soccerRepository.getStandings(competition.leagueID.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _standing.value = it
                _isLoading.value = false
            }, {
                if (it.message.toString() == ExceptionUtil.EXCEPTION_RETURN_OBJECT) {
                    _standing.value = emptyList()
                } else {
                    _error.value = it.message.toString()
                }
                _isLoading.value = false
            }).addTo(disposables)

        soccerRepository.getTeams(competition.leagueID.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (_team.value == null || _team.value != it) {
                    _team.value = it
                }
                _isLoading.value = false
            }, {
                if (it.message.toString() == ExceptionUtil.EXCEPTION_RETURN_OBJECT) {
                    _team.value = emptyList()
                } else {
                    _error.value = it.message.toString()
                }
                _isLoading.value = false
            }).addTo(disposables)
    }

    fun getEventByDateAndLeague(fromDate: String, toDate: String) {
        _isLoading.value = true
        _itemCompetition.observeForever(competitionObserver)

        competitionSubject
            .subscribeOn(Schedulers.io())
            .switchMap {
                soccerRepository.getEvents(it.leagueID, fromDate, toDate)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _event.value = checkNotification(it)
                _isLoading.value = false
            }, {
                if (it.message.toString() == ExceptionUtil.EXCEPTION_RETURN_OBJECT) {
                    _event.value = emptyList()
                } else {
                    _error.value = it.message.toString()
                }
                _isLoading.value = false
            })
            .addTo(disposables)
    }

    private fun checkNotification(events: List<Event>): List<Event> {
        for (item in events) {
            item.isNotification =
                notificationMatch.value?.any { item.matchID == it.matchID } == true
        }
        return events
    }

    fun addNotification(item: Event) {
        soccerRepository.insertEventNotification(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                matchID = item.matchID
                isNotify.value = true
                getEventUpdateNotification()
                _addEvent.value = item
            }, {
                _error.value = it.message.toString()
            }).addTo(disposables)
    }

    fun deleteNotification(item: Event) {
        soccerRepository.deleteEventNotification(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                matchID = item.matchID
                isNotify.value = false
                getEventUpdateNotification()
                _deleteEvent.value = item
            }, {
                _error.value = it.message.toString()
            }).addTo(disposables)
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

    private fun getEventNotification() {
        _isLoading.value = true
        soccerRepository.getEventNotifications()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _notificationMatch.value = it
                _isLoading.value = false
            }, {
                _error.value = it.message.toString()
                _isLoading.value = false
            }).addTo(disposables)
    }

    private fun getEventUpdateNotification() {
        soccerRepository.getEventNotifications()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ events ->
                _notificationMatch.value = events
                _event.value = _event.value?.let { checkNotification(it) }
            }, {
                _error.value = it.message.toString()
            }).addTo(disposables)
    }

    private fun getLeaguesByCountry(countryID: String) {
        _isLoading.value = true
        soccerRepository.getLeagues(countryID.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _competition.value = it
                _isLoading.value = false
            }, {
                _error.value = it.message.toString()
                _isLoading.value = false
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
