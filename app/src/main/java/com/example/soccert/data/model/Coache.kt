package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Coache(
    @SerializedName("coach_age")
    val coachAge: String,
    @SerializedName("coach_country")
    val coachCountry: String,
    @SerializedName("coach_name")
    val coachName: String
)
