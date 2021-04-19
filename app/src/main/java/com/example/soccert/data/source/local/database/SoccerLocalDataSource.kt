package com.example.soccert.data.source.local.database

import com.example.soccert.data.model.Event
import com.example.soccert.data.model.Team
import com.example.soccert.data.source.SoccerDataSource
import com.example.soccert.data.source.local.database.dao.EventNotificationDao
import com.example.soccert.data.source.local.database.dao.TeamDao
import io.reactivex.rxjava3.core.Completable

class SoccerLocalDataSource(
    private val teamDao: TeamDao,
    private val eventNotificationDao: EventNotificationDao
) : SoccerDataSource.Local {

    override fun getTeamsFavorite() = teamDao.getFavorites()

    override fun isTeamFavorite(teamKey: Int) = teamDao.isFavorite(teamKey)

    override fun insertTeamFavorite(team: Team) = teamDao.insertFavorite(team)

    override fun deleteTeamFavorite(team: Team) = teamDao.deleteFavorite(team)

    override fun getEventNotifications() = eventNotificationDao.getEventNotifications()

    override fun getItemEventNotification(matchID: Int) =
        eventNotificationDao.getItemEventNotification(matchID)

    override fun insertEventNotification(event: Event) =
        eventNotificationDao.insertEventNotification(event)

    override fun deleteEventNotification(event: Event) =
        eventNotificationDao.deleteEventNotification(event)
}
