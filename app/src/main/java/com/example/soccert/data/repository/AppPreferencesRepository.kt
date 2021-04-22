package com.example.soccert.data.repository

interface AppPreferencesRepository {
    fun setLanguage(localKey: String)
    fun getLanguage(): String
    fun setCountryID(countryID: String)
    fun getCountryID(): String
}
