package com.example.soccert.data.repository

interface AppPreferencesRepository {
    fun setLanguage(localKey: String)
    fun getLanguage(): String
    fun setCompetitionType(competitionValue: Boolean)
    fun getCompetitionType(): Boolean
}
