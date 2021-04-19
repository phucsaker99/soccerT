package com.example.soccert.data.repository

import com.example.soccert.data.model.Event
import com.example.soccert.data.model.Team
import com.example.soccert.data.source.SoccerDataSource

class SoccerRepositoryImp(
    private val local: SoccerDataSource.Local,
    private val remote: SoccerDataSource.Remote
) : SoccerRepository {

    override fun getCountries() = remote.getCountries()

    override fun getLeagues(countryID: Int) = remote.getLeagues(countryID)

    override fun getTeams(countryID: Int) = remote.getTeams(countryID)

    override fun getTeam(teamID: Int) = remote.getTeam(teamID)

    override fun getPlayer(playerName: String) = remote.getPlayer(playerName)

    override fun getStandings(leagueID: Int) = remote.getStandings(leagueID)

    override fun getEvents(leagueID: Int, from: String, to: String) =
        remote.getEvents(leagueID, from, to)

    override fun getMatch(matchID: Int) = remote.getMatch(matchID)

    override fun getTeamsFavorite() = local.getTeamsFavorite()

    override fun isTeamFavorite(teamKey: Int) = local.isTeamFavorite(teamKey)

    override fun insertTeamFavorite(team: Team) = local.insertTeamFavorite(team)

    override fun deleteTeamFavorite(team: Team) = local.deleteTeamFavorite(team)

    override fun getEventNotifications() = local.getEventNotifications()

    override fun getItemEventNotification(matchID: Int) = local.getItemEventNotification(matchID)

    override fun insertEventNotification(event: Event) = local.insertEventNotification(event)

    override fun deleteEventNotification(event: Event) = local.deleteEventNotification(event)
}
