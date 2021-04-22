package com.example.soccert.data.repository

import com.example.soccert.data.source.AppPreferencesDataSource

class AppPreferencesRepositoryImp(
    private val local: AppPreferencesDataSource
) : AppPreferencesRepository {

    override fun setLanguage(localKey: String) = local.setLanguage(localKey)

    override fun getLanguage() = local.getLanguage()

    override fun setCountryID(countryID: String) = local.setCountryID(countryID)

    override fun getCountryID() = local.getCountryID()
}
