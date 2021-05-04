package com.example.soccert.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Card(
    @SerializedName("card")
    val card: String,
    @SerializedName("home_fault")
    val homeFault: String,
    @SerializedName("time")
    val time: String
) : Parcelable
