package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Substitute(
    @SerializedName("lineup_number")
    val lineup_number: String,
    @SerializedName("lineup_player")
    val lineup_player: String,
    @SerializedName("lineup_position")
    val lineup_position: String
)
