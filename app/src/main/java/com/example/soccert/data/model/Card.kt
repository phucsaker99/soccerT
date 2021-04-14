package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("card")
    val card: String,
    @SerializedName("home_fault")
    val homeFault: String,
    @SerializedName("time")
    val time: String
)
