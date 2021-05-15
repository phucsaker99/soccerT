package com.example.soccert.utils

import androidx.room.TypeConverter
import com.example.soccert.data.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListTypeConverter {
    @TypeConverter
    fun toJsonCoache(list: List<Coache>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJsonCoache(value: String): List<Coache>? {
        val listType = object : TypeToken<List<Coache>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJsonPlayer(list: List<Player>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJsonPlayer(value: String): List<Player>? {
        val listType = object : TypeToken<List<Player>?>() {}.type
        return Gson().fromJson(value, listType)
    }
}
