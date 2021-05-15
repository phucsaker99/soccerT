package com.example.soccert.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.soccert.service.AlarmManagerService

class SoccerBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        AlarmManagerService.enqueueWork(context, intent)
    }
}
