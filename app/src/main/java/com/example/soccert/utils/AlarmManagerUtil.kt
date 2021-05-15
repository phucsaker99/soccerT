package com.example.soccert.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.soccert.broadcast.SoccerBroadcast

object AlarmManagerUtil {
    fun create(context: Context, matchID: String, timeChoose: Long) {
        val intent = Intent(context.applicationContext, SoccerBroadcast::class.java).apply {
            putExtra(AlarmConst.EXTRA_SOCCER_ID, matchID)
        }
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val pendingIntent =
            PendingIntent.getBroadcast(
                context.applicationContext,
                matchID.toInt(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        alarmManager?.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + 1000*10,
            pendingIntent
        )
    }

    fun cancel(context: Context, matchID: String) {
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(context, SoccerBroadcast::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, matchID.toInt(), intent, 0)
        alarmManager?.cancel(pendingIntent)
    }
}
