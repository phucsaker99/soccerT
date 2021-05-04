package com.example.soccert.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Substitutions(
    @SerializedName("away")
    val away: List<AwayReplace>,
    @SerializedName("home")
    val home: List<HomeReplace>
) : Parcelable
