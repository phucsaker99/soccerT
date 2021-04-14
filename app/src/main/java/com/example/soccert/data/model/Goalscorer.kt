package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Goalscorer(
    @SerializedName("home_scorer")
    val home_scorer: String,
    @SerializedName("score")
    val score: String,
    @SerializedName("time")
    val time: String
)
