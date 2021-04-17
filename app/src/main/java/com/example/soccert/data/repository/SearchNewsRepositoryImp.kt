package com.example.soccert.data.repository

import com.example.soccert.data.model.News
import com.example.soccert.data.source.SearchNewsDataSource
import io.reactivex.rxjava3.core.Observable

class SearchNewsRepositoryImp(
    private val remote: SearchNewsDataSource.Remote
) : SearchNewsRepository {

    override fun searchNews(search: String): Observable<List<News>> =
        remote.searchNews(search).map { it.articles }
}
