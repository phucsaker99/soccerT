package com.example.soccert.data.source

import com.example.soccert.data.model.*
import io.reactivex.rxjava3.core.Observable

interface SoccerDataSource {
    interface Local

    interface Remote {
        fun getCountries(): Observable<List<Country>>
        fun getLeagues(countryID: Int): Observable<List<Competition>>
        fun getTeams(countryID: Int): Observable<List<Team>>
        fun getTeam(teamID: Int): Observable<List<Team>>
        fun getPlayer(playerName: String): Observable<List<Player>>
        fun getStandings(leagueID: Int): Observable<List<Standing>>
        fun getEvents(leagueID: Int, from: String, to: String): Observable<List<Event>>
        fun getMatch(matchID: Int): Observable<List<Event>>
    }
}
