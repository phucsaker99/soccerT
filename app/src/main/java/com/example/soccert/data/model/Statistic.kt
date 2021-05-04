package com.example.soccert.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
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
) : Parcelable {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Statistic>() {
            override fun areItemsTheSame(oldItem: Statistic, newItem: Statistic) =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: Statistic, newItem: Statistic) =
                oldItem == newItem
        }
    }
}
