package com.example.soccert.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country_id")
    val countryID: String,
    @SerializedName("country_logo")
    val countryLogo: String,
    @SerializedName("country_name")
    val countryName: String
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country) =
                oldItem.countryID == newItem.countryID

            override fun areContentsTheSame(oldItem: Country, newItem: Country) = oldItem == newItem
        }
    }
}
