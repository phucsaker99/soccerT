package com.example.soccert.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeReplace(
    @SerializedName("substitution")
    val substitution: String,
    @SerializedName("time")
    val time: String
) : Parcelable
