package com.example.soccert.data.source

import com.example.soccert.data.model.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface SoccerDataSource {
    interface Local {
        fun getTeamsFavorite(): Observable<List<Team>>
        fun isTeamFavorite(teamKey: Int): Single<List<Team>>
        fun insertTeamFavorite(team: Team): Completable
        fun deleteTeamFavorite(team: Team): Completable

        fun getEventNotifications(): Observable<List<Event>>
        fun getItemEventNotification(matchID: Int): Single<List<Event>>
        fun insertEventNotification(event: Event): Completable
        fun deleteEventNotification(event: Event): Completable
    }

    interface Remote {
        fun getCountries(): Observable<List<Country>>
        fun getLeagues(countryID: Int): Observable<List<Competition>>
        fun getTeams(countryID: Int): Observable<List<Team>>
        fun getTeam(teamID: Int): Observable<List<Team>>
        fun getPlayer(playerName: String): Observable<List<Player>>
        fun getStandings(leagueID: Int): Observable<List<Standing>>
        fun getEvents(leagueID: String, from: String, to: String): Observable<List<Event>>
        fun getMatch(matchID: Int): Observable<List<Event>>
    }
}
