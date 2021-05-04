package com.example.soccert.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coach(
    @SerializedName("lineup_number")
    val lineupNumber: String,
    @SerializedName("lineup_player")
    val lineupPlayer: String,
    @SerializedName("lineup_position")
    val lineupPosition: String
) : Parcelable {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Coach>() {
            override fun areItemsTheSame(oldItem: Coach, newItem: Coach) =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: Coach, newItem: Coach) =
                oldItem == newItem
        }
    }
}
