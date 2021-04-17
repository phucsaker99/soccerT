package com.example.soccert.data.source.remote.utils

import com.example.soccert.data.model.NewsResponse
import com.example.soccert.utils.ApiEndPoint.GET_SEARCH_SPORTS
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET(GET_SEARCH_SPORTS)
    fun searchNews(@Query("q") search: String): Observable<NewsResponse>
}
