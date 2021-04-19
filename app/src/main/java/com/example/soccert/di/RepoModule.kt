package com.example.soccert.di

import com.example.soccert.data.repository.SearchNewsRepository
import com.example.soccert.data.repository.SearchNewsRepositoryImp
import com.example.soccert.data.source.SearchNewsDataSource
import com.example.soccert.data.source.SoccerDataSource
import com.example.soccert.data.source.remote.SearchNewRemoteDataSource
import com.example.soccert.data.source.remote.SoccerRemoteDataSource
import com.example.soccert.utils.KoinConfig.SEARCH_SERVICE
import com.example.soccert.utils.KoinConfig.SOCCER_SERVICE
import org.koin.core.qualifier.named
import org.koin.dsl.module

val soccerRepoModule = module {
    single<SoccerDataSource.Remote> { SoccerRemoteDataSource(get(named(SOCCER_SERVICE))) }
}

val searchNewsRepoModule = module {
    single<SearchNewsDataSource.Remote> { SearchNewRemoteDataSource(get(named(SEARCH_SERVICE))) }
    single<SearchNewsRepository> { SearchNewsRepositoryImp(get()) }
}
