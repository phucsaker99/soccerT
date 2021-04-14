package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Statistic(
    @SerializedName("away")
    val away: String,
    @SerializedName("home")
    val home: String,
    @SerializedName("type")
    val type: String
)
