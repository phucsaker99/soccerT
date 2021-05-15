package com.example.soccert.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccert.base.RxViewModel
import com.example.soccert.data.model.News
import com.example.soccert.data.repository.SearchNewsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsViewModel(
    private val searchNewsRepository: SearchNewsRepository
) : RxViewModel() {

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getDefaultNews()
    }

    private fun getDefaultNews() {
        _isLoading.value = true
        searchNewsRepository.searchNews(DEFAULT_NEWS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _news.value = it
                _isLoading.value = false
            }, {
                _error.value = it.message.toString()
                _isLoading.value = false
            }).addTo(disposables)
    }

    fun filterProduct(newsKey: String) {
        if (newsKey.isEmpty()) return
        _isLoading.value = true
        searchNewsRepository.searchNews(newsKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _news.value = it
                _isLoading.value = false
            }, {
                _error.value = it.message.toString()
                _isLoading.value = false
            }).addTo(disposables)
    }

    companion object {
        const val DEFAULT_NEWS = "ronaldo"
    }
}
