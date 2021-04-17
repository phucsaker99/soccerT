package com.example.soccert.data.repository

import com.example.soccert.data.model.News
import io.reactivex.rxjava3.core.Observable

interface SearchNewsRepository {
    fun searchNews(search: String): Observable<List<News>>
}
