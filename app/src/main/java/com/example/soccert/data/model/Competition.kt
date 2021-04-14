package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Competition(
    @SerializedName("league_id")
    val leagueID: String,
    @SerializedName("league_name")
    val leagueName: String
)
