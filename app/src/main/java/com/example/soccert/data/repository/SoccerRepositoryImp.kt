package com.example.soccert.data.repository

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
}
