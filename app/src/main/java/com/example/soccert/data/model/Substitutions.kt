package com.example.soccert.data.model

import com.google.gson.annotations.SerializedName

data class Substitutions(
    @SerializedName("away")
    val away: List<AwayReplace>,
    @SerializedName("home")
    val home: List<HomeReplace>
)
