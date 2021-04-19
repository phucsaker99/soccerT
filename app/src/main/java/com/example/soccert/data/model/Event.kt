package com.example.soccert.data.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "EventNotification")
data class Event(
    @PrimaryKey
    @ColumnInfo(name = "match_id")
    @SerializedName("match_id")
    val matchID: String,
    @ColumnInfo(name = "matchmatch_hometeam_id_id")
    @SerializedName("match_hometeam_id")
    val matchHomeTeamID: String,
    @ColumnInfo(name = "match_hometeam_name")
    @SerializedName("match_hometeam_name")
    val matchHomeTeamName: String,
    @ColumnInfo(name = "match_hometeam_score")
    @SerializedName("match_hometeam_score")
    val matchHomeTeamScore: String,
    @Ignore
    @SerializedName("match_hometeam_system")
    val matchHomeTeamSystem: String?,
    @ColumnInfo(name = "team_home_badge")
    @SerializedName("team_home_badge")
    val teamHomeBadge: String,
    @ColumnInfo(name = "match_awayteam_id")
    @SerializedName("match_awayteam_id")
    val matchAwayTeamID: String,
    @ColumnInfo(name = "match_awayteam_name")
    @SerializedName("match_awayteam_name")
    val matchAwayTeamName: String,
    @ColumnInfo(name = "match_awayteam_score")
    @SerializedName("match_awayteam_score")
    val matchAwayTeamScore: String,
    @Ignore
    @SerializedName("match_awayteam_system")
    val matchAwayTeamSystem: String?,
    @ColumnInfo(name = "team_away_badge")
    @SerializedName("team_away_badge")
    val teamAwayBadge: String,
    @ColumnInfo(name = "match_date")
    @SerializedName("match_date")
    val matchDate: String,
    @ColumnInfo(name = "match_time")
    @SerializedName("match_time")
    val matchTime: String,
    @Ignore
    @SerializedName("match_status")
    val matchStatus: String?,
    @Ignore
    @SerializedName("statistics")
    val statistics: List<Statistic>?,
    @Ignore
    @SerializedName("goalscorer")
    val goalscorer: List<Goalscorer>?,
    @Ignore
    @SerializedName("cards")
    val cards: List<Card>?,
    @Ignore
    @SerializedName("lineup")
    val lineup: Lineup?,
    @Ignore
    @SerializedName("substitutions")
    val substitutions: Substitutions?
) {

    constructor(
        matchID: String,
        matchHomeTeamID: String,
        matchHomeTeamName: String,
        matchHomeTeamScore: String,
        teamHomeBadge: String,
        matchAwayTeamID: String,
        matchAwayTeamName: String,
        matchAwayTeamScore: String,
        teamAwayBadge: String,
        matchDate: String,
        matchTime: String
    ) : this(
        matchID,
        matchHomeTeamID,
        matchHomeTeamName,
        matchHomeTeamScore,
        null,
        teamHomeBadge,
        matchAwayTeamID,
        matchAwayTeamName,
        matchAwayTeamScore,
        null,
        teamAwayBadge,
        matchDate,
        matchTime,
        null,
        null,
        null,
        null,
        null,
        null
    )

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event) =
                oldItem.matchID == newItem.matchID

            override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
        }
    }
}
