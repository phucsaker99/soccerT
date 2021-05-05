package com.example.soccert.ui.detailteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccert.base.RxViewModel
import com.example.soccert.data.model.Team
import com.example.soccert.data.repository.SoccerRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailTeamViewModel(
    private val soccerRepository: SoccerRepository
) : RxViewModel() {

    private val _team = MutableLiveData<Team>()
    val team: LiveData<Team> get() = _team

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun getInitTeam(team: Team) {
        setUpTeam(team)
        checkFavoriteTeam(team)
    }

    private fun setUpTeam(team: Team) {
        _team.value = team
    }

    private fun checkFavoriteTeam(team: Team) {
        soccerRepository.isTeamFavorite(team.teamKey.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isFavorite.value = it.isNotEmpty()
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }
}
