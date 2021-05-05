package com.example.soccert.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Team")
@Parcelize
data class Team(
    @PrimaryKey
    @ColumnInfo(name = "team_key")
    @SerializedName("team_key")
    val teamKey: String,
    @ColumnInfo(name = "team_name")
    @SerializedName("team_name")
    val teamName: String,
    @ColumnInfo(name = "team_badge")
    @SerializedName("team_badge")
    val teamBadge: String,
    @Ignore
    @SerializedName("coaches")
    val coaches: List<Coache>?,
    @Ignore
    @SerializedName("players")
    val players: List<Player>?
) : Parcelable {

    constructor(teamKey: String, teamName: String, teamBadge: String) : this(
        teamKey,
        teamName,
        teamBadge,
        null,
        null
    )

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Team>() {
            override fun areItemsTheSame(oldItem: Team, newItem: Team) =
                oldItem.teamKey == newItem.teamKey

            override fun areContentsTheSame(oldItem: Team, newItem: Team) = oldItem == newItem
        }
    }
}
