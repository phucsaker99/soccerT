package com.example.soccert

import android.app.Application
import com.example.soccert.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    netWorkModule,
                    apiModule,
                    dbModule,
                    soccerRepoModule,
                    searchNewsRepoModule,
                    prefsRepoModule
                )
            )
        }
    }
}
