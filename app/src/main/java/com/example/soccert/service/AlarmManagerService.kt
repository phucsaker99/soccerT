package com.example.soccert.service

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.request.target.NotificationTarget
import com.example.soccert.MyApplication.Companion.CHANNEL_SOCCERT_ID
import com.example.soccert.R
import com.example.soccert.data.model.Event
import com.example.soccert.data.repository.SoccerRepository
import com.example.soccert.ui.main.MainActivity
import com.example.soccert.utils.AlarmConst
import com.example.soccert.utils.GlideApp
import com.example.soccert.utils.ToastType
import com.example.soccert.utils.showToast
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.get

class AlarmManagerService : JobIntentService() {
    private lateinit var notificationManager: NotificationManagerCompat
    private val disposables: CompositeDisposable = CompositeDisposable()
    private var repository: SoccerRepository? = null

    override fun onHandleWork(intent: Intent) {
        val matchId = intent.getStringExtra(AlarmConst.EXTRA_SOCCER_ID)

        repository = get()
        repository?.let {
            it.getItemEventNotification(matchId!!.toInt())
                .subscribeOn(Schedulers.io())
                .subscribe({ events ->
                    events.forEach { event ->
                        if (event.matchID == matchId) {
                            showNotification(event)
                        }
                    }
                }, {
                    this@AlarmManagerService.showToast(
                        ToastType.Error,
                        getString(R.string.error_default)
                    )
                }).addTo(disposables)
        }
    }

    private fun showNotification(event: Event) {
        createNotificationChannel(event)
        deleteNotificationChannel(event)
    }

    private fun createNotificationChannel(event: Event) {
        notificationManager = NotificationManagerCompat.from(this)

        val intent = MainActivity.getIntentFromNotification(this)
        val pendingIntent = PendingIntent.getActivity(
            this,
            event.matchID.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val expandedView = RemoteViews(packageName, R.layout.notification_expanse_event)
        val collapseView = RemoteViews(packageName, R.layout.notification_collap_event)

        val notification = NotificationCompat.Builder(this, CHANNEL_SOCCERT_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setAutoCancel(true)
            .setCustomBigContentView(expandedView)
            .setCustomContentView(collapseView)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_EMAIL)
            .setContentIntent(pendingIntent)
            .build()

        expandedView.apply {
            setTextViewText(R.id.textHome, event.matchHomeTeamName)
            setTextViewText(R.id.textAway, event.matchAwayTeamName)
            GlideApp.with(applicationContext)
                .asBitmap()
                .load(event.teamHomeBadge)
                .fitCenter()
                .placeholder(R.drawable.vietnam_flag)
                .into(
                    NotificationTarget(
                        applicationContext,
                        R.id.imageHomeNotification,
                        expandedView,
                        notification,
                        CHANNEL_SOCCERT_ID.toInt()
                    )
                )

            GlideApp.with(applicationContext)
                .asBitmap()
                .load(event.teamAwayBadge)
                .fitCenter()
                .placeholder(R.drawable.usa_flag)
                .into(
                    NotificationTarget(
                        applicationContext,
                        R.id.imageAwayNotification,
                        expandedView,
                        notification,
                        CHANNEL_SOCCERT_ID.toInt()
                    )
                )
        }

        notificationManager.notify(event.matchID.toInt(), notification)
    }

    private fun deleteNotificationChannel(event: Event) {
        repository!!.deleteEventNotification(event)
            .subscribeOn(Schedulers.io())
            .subscribe({}, {
                this@AlarmManagerService.showToast(
                    ToastType.Error,
                    getString(R.string.error_delete_notification)
                )
            }).addTo(disposables)

        isStopped
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    companion object {
        private const val JOB_ID = 1

        fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, AlarmManagerService::class.java, JOB_ID, work)
        }
    }
}
