package com.example.soccert.data.source.remote.utils

import com.example.soccert.data.model.*
import com.example.soccert.utils.ApiEndPoint
import com.example.soccert.utils.ApiEndPoint.PARAMS_COUNTRY_ID
import com.example.soccert.utils.ApiEndPoint.PARAMS_FROM
import com.example.soccert.utils.ApiEndPoint.PARAMS_LEAGUE_ID
import com.example.soccert.utils.ApiEndPoint.PARAMS_MATCH_ID
import com.example.soccert.utils.ApiEndPoint.PARAMS_PLAYER_NAME
import com.example.soccert.utils.ApiEndPoint.PARAMS_TEAM_ID
import com.example.soccert.utils.ApiEndPoint.PARAMS_TO
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

interface SoccerApiService {

    @GET(ApiEndPoint.GET_COUNTRIES)
    fun getCountries(): Observable<List<Country>>

    @GET(ApiEndPoint.GET_LEAGUES)
    fun getLeagues(@Query(PARAMS_COUNTRY_ID) countryID: Int): Observable<List<Competition>>

    @GET(ApiEndPoint.GET_TEAMS)
    fun getTeams(@Query(PARAMS_COUNTRY_ID) countryID: Int): Observable<List<Team>>

    @GET(ApiEndPoint.GET_TEAMS)
    fun getTeam(@Query(PARAMS_TEAM_ID) teamID: Int): Observable<List<Team>>

    @GET(ApiEndPoint.GET_PLAYERS)
    fun getPlayer(@Query(PARAMS_PLAYER_NAME) playerName: String): Observable<List<Player>>

    @GET(ApiEndPoint.GET_STANDINGS)
    fun getStandings(@Query(PARAMS_LEAGUE_ID) leagueID: Int): Observable<List<Standing>>

    @GET(ApiEndPoint.GET_EVENTS)
    fun getEvents(
        @Query(PARAMS_LEAGUE_ID) leagueID: Int,
        @Query(PARAMS_FROM) from: String = LocalDate.now().minusDays(10).toString(),
        @Query(PARAMS_TO) to: String = LocalDate.now().plusDays(10).toString()
    ): Observable<List<Event>>

    @GET(ApiEndPoint.GET_EVENTS)
    fun getMatch(@Query(PARAMS_MATCH_ID) matchID: Int): Observable<List<Event>>
}
