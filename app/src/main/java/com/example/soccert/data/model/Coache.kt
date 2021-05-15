package com.example.soccert.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coache(
    @SerializedName("coach_age")
    val coachAge: String,
    @SerializedName("coach_country")
    val coachCountry: String,
    @SerializedName("coach_name")
    val coachName: String
) : Parcelable
