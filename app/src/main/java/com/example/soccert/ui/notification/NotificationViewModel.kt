package com.example.soccert.ui.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccert.base.RxViewModel
import com.example.soccert.data.model.Event
import com.example.soccert.data.repository.SoccerRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class NotificationViewModel(
    private val soccerRepository: SoccerRepository
) : RxViewModel() {

    private val _notifications = MutableLiveData<List<Event>>()
    val notifications: LiveData<List<Event>> get() = _notifications

    var isNotify = MutableLiveData<Boolean>(false)
    var matchID = ""

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getNotifications()
    }

    private fun getNotifications() {
        _isLoading.value = true
        soccerRepository.getEventNotifications()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _notifications.value = it
                _isLoading.value = false
            }, {
                _error.value = it.message.toString()
                _isLoading.value = false
            }).addTo(disposables)
    }

    fun deleteNotification(item: Event) {
        soccerRepository.deleteEventNotification(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                matchID = item.matchID
                isNotify.value = false
            }, {
                _error.value = it.message.toString()
            }).addTo(disposables)
    }
}
