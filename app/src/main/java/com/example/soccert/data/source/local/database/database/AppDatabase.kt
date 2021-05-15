package com.example.soccert.data.source.local.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.soccert.data.model.Event
import com.example.soccert.data.model.Team
import com.example.soccert.data.source.local.database.database.AppDatabase.Companion.DATABASE_VERSION
import com.example.soccert.data.source.local.database.database.AppDatabase.Companion.EXPORT_SCHEME
import com.example.soccert.data.source.local.database.dao.EventNotificationDao
import com.example.soccert.data.source.local.database.dao.TeamDao
import com.example.soccert.utils.ListTypeConverter

@Database(
    entities = [Team::class, Event::class],
    version = DATABASE_VERSION,
    exportSchema = EXPORT_SCHEME
)
@TypeConverters(ListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun eventNotificationDao(): EventNotificationDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "soccerT"
        const val EXPORT_SCHEME = false
    }
}
