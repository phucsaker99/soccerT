package com.example.soccert

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.widget.Toast
import com.example.soccert.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    netWorkModule,
                    apiModule,
                    dbModule,
                    soccerRepoModule,
                    searchNewsRepoModule,
                    prefsRepoModule,
                    viewModelModule
                )
            )
        }
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel1 = NotificationChannel(
                CHANNEL_SOCCERT_ID,
                NAME_SYSTEM_1,
                NotificationManager.IMPORTANCE_HIGH
            )
            channel1.description = "This is channel SoccerT 1"

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.apply {
                createNotificationChannel(channel1)
            }
        }
    }

    companion object {
        const val CHANNEL_SOCCERT_ID = "050399"
        const val NAME_SYSTEM_1 = "SoccerT_1"
    }
}
