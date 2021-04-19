package com.example.soccert.data.source.remote

import com.example.soccert.data.source.SoccerDataSource
import com.example.soccert.data.source.remote.utils.SoccerApiService

class SoccerRemoteDataSource(
    private val soccerApiService: SoccerApiService
) : SoccerDataSource.Remote {

    override fun getCountries() = soccerApiService.getCountries()

    override fun getLeagues(countryID: Int) = soccerApiService.getLeagues(countryID)

    override fun getTeams(countryID: Int) = soccerApiService.getTeams(countryID)

    override fun getTeam(teamID: Int) = soccerApiService.getTeam(teamID)

    override fun getPlayer(playerName: String) = soccerApiService.getPlayer(playerName)

    override fun getStandings(leagueID: Int) = soccerApiService.getStandings(leagueID)

    override fun getEvents(leagueID: Int, from: String, to: String) =
        soccerApiService.getEvents(leagueID, from, to)

    override fun getMatch(matchID: Int) = soccerApiService.getMatch(matchID)
}
