package com.example.soccert.data.source.local.database.dao

import androidx.room.*
import com.example.soccert.data.model.Event
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface EventNotificationDao {
    @Query("Select * from EventNotification")
    fun getEventNotifications(): Observable<List<Event>>

    @Query("SELECT * FROM EventNotification WHERE match_id =:matchID")
    fun getItemEventNotification(matchID: Int): Single<List<Event>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEventNotification(event: Event): Completable

    @Delete
    fun deleteEventNotification(event: Event): Completable
}
