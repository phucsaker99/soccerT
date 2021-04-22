package com.example.soccert.data.source

interface AppPreferencesDataSource {
    fun setLanguage(languageValue: String)
    fun getLanguage(): String
    fun setCountryID(countryID: String)
    fun getCountryID(): String
}
