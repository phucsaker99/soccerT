package com.example.soccert.data.source.local.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.soccert.R
import com.example.soccert.utils.SharePreferencesConst.PREFS_NAME

@Suppress("UNCHECKED_CAST")
class AppPreferencesHelper(private val context: Context) {
    private var sharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    fun <T> put(key: String, data: T) {
        val editor = sharedPreferences.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Int -> editor.putInt(key, data)
        }
        editor.apply()
    }

    fun <T> get(key: String, data: T): T = when (data) {
        is String -> sharedPreferences.getString(key, null) as T
        is Boolean -> sharedPreferences.getBoolean(key, false) as T
        is Int -> sharedPreferences.getInt(key, 0) as T
        else -> throw TypeCastException(context.resources.getString(R.string.error_convert_type))
    }
}
