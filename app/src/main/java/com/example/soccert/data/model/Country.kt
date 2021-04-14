package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country_id")
    val countryID: String,
    @SerializedName("country_logo")
    val countryLogo: String,
    @SerializedName("country_name")
    val countryName: String
)
