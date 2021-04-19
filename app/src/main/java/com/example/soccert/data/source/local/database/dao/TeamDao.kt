package com.example.soccert.data.source.local.database.dao

import androidx.room.*
import com.example.soccert.data.model.Team
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface TeamDao {
    @Query("SELECT * FROM Team")
    fun getFavorites(): Observable<List<Team>>

    @Query("SELECT * FROM team WHERE team_key =:teamKey")
    fun isFavorite(teamKey: Int): Single<List<Team>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavorite(team: Team): Completable

    @Delete
    fun deleteFavorite(team: Team): Completable
}
