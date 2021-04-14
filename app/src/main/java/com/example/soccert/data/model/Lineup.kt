package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Lineup(
    @SerializedName("home")
    val home: Home,
    @SerializedName("away")
    val away: Away
)
