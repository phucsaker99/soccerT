package com.example.soccert.utils

import android.annotation.SuppressLint
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate

@SuppressLint("UseSwitchCompatOrMaterialCode")
fun Switch.checkTheme(){
    setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
