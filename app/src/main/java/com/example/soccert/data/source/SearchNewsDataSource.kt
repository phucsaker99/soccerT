package com.example.soccert.data.source

import com.example.soccert.data.model.NewsResponse
import io.reactivex.rxjava3.core.Observable

interface SearchNewsDataSource {
    interface Remote {
        fun searchNews(search: String): Observable<NewsResponse>
    }
}
