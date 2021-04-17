package com.example.soccert.data.source.remote

import com.example.soccert.data.source.SearchNewsDataSource
import com.example.soccert.data.source.remote.utils.SearchApiService

class SearchNewRemoteDataSource(
    private val searchApiService: SearchApiService
) : SearchNewsDataSource.Remote {

    override fun searchNews(search: String) = searchApiService.searchNews(search)
}
