package com.example.soccert.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Standing(
    @SerializedName("overall_league_position")
    val overallLeaguePosition: String,
    @SerializedName("team_id")
    val teamID: String,
    @SerializedName("team_name")
    val teamName: String,
    @SerializedName("overall_league_payed")
    val overallLeaguePayed: String,
    @SerializedName("overall_league_W")
    val overallLeagueW: String,
    @SerializedName("overall_league_D")
    val overallLeagueD: String,
    @SerializedName("overall_league_L")
    val overallLeagueL: String,
    @SerializedName("overall_league_GA")
    val overallLeagueGA: String,
    @SerializedName("overall_league_GF")
    val overallLeagueGF: String,
    @SerializedName("overall_league_PTS")
    val overallLeaguePTS: String
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Standing>() {
            override fun areItemsTheSame(oldItem: Standing, newItem: Standing) =
                oldItem.teamID == newItem.teamID

            override fun areContentsTheSame(oldItem: Standing, newItem: Standing) =
                oldItem == newItem
        }
    }
}
