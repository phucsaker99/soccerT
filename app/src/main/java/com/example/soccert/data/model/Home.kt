package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Home(
    @SerializedName("coach")
    val coach: List<Coach>,
    @SerializedName("missing_players")
    val missingPlayers: List<MissingPlayer>,
    @SerializedName("starting_lineups")
    val startingLineups: List<StartingLineup>,
    @SerializedName("substitutes")
    val substitutes: List<Substitute>
)
