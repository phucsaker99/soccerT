package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Standing(
    @SerializedName("overall_league_position")
    val overallLeaguePosition: String,
    @SerializedName("team_id")
    val teamID: String,
    @SerializedName("team_name")
    val teamName: String,
    @SerializedName("overall_league_payed")
    val overallLeaguePayed: String,
    @SerializedName("overall_league_W")
    val overallLeagueW: String,
    @SerializedName("overall_league_D")
    val overallLeagueD: String,
    @SerializedName("overall_league_L")
    val overallLeagueL: String,
    @SerializedName("overall_league_GA")
    val overallLeagueGA: String,
    @SerializedName("overall_league_GF")
    val overallLeagueGF: String,
    @SerializedName("overall_league_PTS")
    val overallLeaguePTS: String
)
