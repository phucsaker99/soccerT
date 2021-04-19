package com.example.soccert.di

import androidx.room.Room
import com.example.soccert.data.repository.*
import com.example.soccert.data.source.AppPreferencesDataSource
import com.example.soccert.data.source.SearchNewsDataSource
import com.example.soccert.data.source.SoccerDataSource
import com.example.soccert.data.source.local.AppPreferencesLocalDataSource
import com.example.soccert.data.source.local.database.database.AppDatabase
import com.example.soccert.data.source.local.database.database.AppDatabase.Companion.DATABASE_NAME
import com.example.soccert.data.source.local.database.SoccerLocalDataSource
import com.example.soccert.data.source.local.pref.AppPreferencesHelper
import com.example.soccert.data.source.remote.SearchNewRemoteDataSource
import com.example.soccert.data.source.remote.SoccerRemoteDataSource
import com.example.soccert.utils.KoinConfig.SEARCH_SERVICE
import com.example.soccert.utils.KoinConfig.SOCCER_SERVICE
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val soccerRepoModule = module {
    single<SoccerDataSource.Remote> { SoccerRemoteDataSource(get(named(SOCCER_SERVICE))) }
    single<SoccerDataSource.Local> { SoccerLocalDataSource(get(), get()) }
    single<SoccerRepository> { SoccerRepositoryImp(get(), get()) }
}

val searchNewsRepoModule = module {
    single<SearchNewsDataSource.Remote> { SearchNewRemoteDataSource(get(named(SEARCH_SERVICE))) }
    single<SearchNewsRepository> { SearchNewsRepositoryImp(get()) }
}

val prefsRepoModule = module {
    single { AppPreferencesHelper(get()) }
    single<AppPreferencesDataSource> { AppPreferencesLocalDataSource(get()) }
    single<AppPreferencesRepository> { AppPreferencesRepositoryImp(get()) }
}

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
    single { get<AppDatabase>().teamDao() }
    single { get<AppDatabase>().eventNotificationDao() }
}
