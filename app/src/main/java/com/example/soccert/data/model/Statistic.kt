package com.example.soccert.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statistic(
    @SerializedName("away")
    val away: String,
    @SerializedName("home")
    val home: String,
    @SerializedName("type")
    val type: String
) : Parcelable
