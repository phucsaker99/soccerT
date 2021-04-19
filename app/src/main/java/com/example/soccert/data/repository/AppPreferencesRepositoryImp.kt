package com.example.soccert.data.repository

import com.example.soccert.data.source.AppPreferencesDataSource

class AppPreferencesRepositoryImp(
    private val local: AppPreferencesDataSource
) : AppPreferencesRepository {

    override fun setLanguage(localKey: String) = local.setLanguage(localKey)

    override fun getLanguage() = local.getLanguage()

    override fun setCompetitionType(competitionValue: Boolean) =
        local.setCompetitionType(competitionValue)

    override fun getCompetitionType() = local.getCompetitionType()
}
