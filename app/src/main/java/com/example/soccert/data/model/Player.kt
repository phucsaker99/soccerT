package com.example.soccert.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    @SerializedName("player_age")
    val playerAge: String,
    @SerializedName("player_country")
    val playerCountry: String,
    @SerializedName("player_goals")
    val playerGoals: String,
    @SerializedName("player_key")
    val playerKey: Double,
    @SerializedName("player_match_played")
    val playerMatchPlayed: String,
    @SerializedName("player_name")
    val playerName: String,
    @SerializedName("player_number")
    val playerNumber: String,
    @SerializedName("player_red_cards")
    val playerRedCards: String,
    @SerializedName("player_type")
    val playerType: String,
    @SerializedName("player_yellow_cards")
    val playerYellowCards: String
) : Parcelable {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Player>() {
            override fun areItemsTheSame(oldItem: Player, newItem: Player) =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: Player, newItem: Player) = oldItem == newItem
        }
    }
}
