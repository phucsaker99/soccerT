package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("player_age")
    val playerAge: String,
    @SerializedName("player_country")
    val playerCountry: String,
    @SerializedName("player_goals")
    val playerGoals: String,
    @SerializedName("player_key")
    val playerKey: Double,
    @SerializedName("player_match_played")
    val playerMatchPlayed: String,
    @SerializedName("player_name")
    val playerName: String,
    @SerializedName("player_number")
    val playerNumber: String,
    @SerializedName("player_red_cards")
    val playerRedCards: String,
    @SerializedName("player_type")
    val playerType: String,
    @SerializedName("player_yellow_cards")
    val playerYellowCards: String
)
