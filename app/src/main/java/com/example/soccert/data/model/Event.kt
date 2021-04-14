package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("match_id")
    val matchID: String,
    @SerializedName("match_hometeam_id")
    val matchHomeTeamID: String,
    @SerializedName("match_hometeam_name")
    val matchHomeTeamName: String,
    @SerializedName("match_hometeam_score")
    val matchHomeTeamScore: String,
    @SerializedName("match_hometeam_system")
    val matchHomeTeamSystem: String,
    @SerializedName("team_home_badge")
    val teamHomeBadge: String,
    @SerializedName("match_awayteam_id")
    val matchAwayTeam_id: String,
    @SerializedName("match_awayteam_name")
    val matchAwayTeamName: String,
    @SerializedName("match_awayteam_score")
    val matchAwayTeamScore: String,
    @SerializedName("match_awayteam_system")
    val matchAwayTeamSystem: String,
    @SerializedName("team_away_badge")
    val teamAwayBadge: String,
    @SerializedName("match_date")
    val matchDate: String,
    @SerializedName("match_time")
    val matchTime: String,
    @SerializedName("match_status")
    val matchStatus: String,
    @SerializedName("statistics")
    val statistics: List<Statistic>,
    @SerializedName("goalscorer")
    val goalscorer: List<Goalscorer>,
    @SerializedName("cards")
    val cards: List<Card>,
    @SerializedName("lineup")
    val lineup: Lineup,
    @SerializedName("substitutions")
    val substitutions: Substitutions
)
