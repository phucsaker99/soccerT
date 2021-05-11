package com.example.soccert.di

import com.example.soccert.data.model.Team
import com.example.soccert.data.source.remote.utils.SearchApiService
import com.example.soccert.data.source.remote.utils.SoccerApiService
import com.example.soccert.utils.KoinConfig.SEARCH_RETROFIT_NAME
import com.example.soccert.utils.KoinConfig.SEARCH_SERVICE
import com.example.soccert.utils.KoinConfig.SOCCER_RETROFIT_NAME
import com.example.soccert.utils.KoinConfig.SOCCER_SERVICE
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.*
import kotlin.collections.ArrayList

val apiModule = module {
    single(named(SOCCER_SERVICE)) {
        get<Retrofit>(named(SOCCER_RETROFIT_NAME)).create(
            SoccerApiService::class.java
        )
    }

    single(named(SEARCH_SERVICE)) {
        get<Retrofit>(named(SEARCH_RETROFIT_NAME)).create(
            SearchApiService::class.java
        )
    }
}
