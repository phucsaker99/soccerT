package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("team_key")
    val teamKey: String,
    @SerializedName("team_name")
    val teamName: String,
    @SerializedName("team_badge")
    val teamBadge: String,
    @SerializedName("coaches")
    val coaches: List<Coache>,
    @SerializedName("players")
    val players: List<Player>
)
