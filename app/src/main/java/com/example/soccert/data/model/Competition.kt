package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Competition(
    @SerializedName("league_id")
    val leagueID: String,
    @SerializedName("league_name")
    val leagueName: String,
    @SerializedName("league_season")
    val leagueSeason: String,
    @SerializedName("league_logo")
    val leagueLogo: String,
    @SerializedName("country_logo")
    val countryLogo: String
)
