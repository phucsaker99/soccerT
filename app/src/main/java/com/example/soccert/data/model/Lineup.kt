package com.example.soccert.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Lineup(
    @SerializedName("home")
    val home: Home,
    @SerializedName("away")
    val away: Away
) : Parcelable
