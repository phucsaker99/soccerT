package com.example.soccert.data.source

interface AppPreferencesDataSource {
    fun setLanguage(languageValue: String)
    fun getLanguage(): String
    fun setCompetitionType(competitionValue: Boolean)
    fun getCompetitionType(): Boolean
}
