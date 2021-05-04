package com.example.soccert.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Goalscorer(
    @SerializedName("home_scorer")
    val home_scorer: String,
    @SerializedName("score")
    val score: String,
    @SerializedName("time")
    val time: String
) : Parcelable
