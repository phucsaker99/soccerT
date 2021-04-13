package com.example.soccert.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.soccert.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val navController by lazy { navHostFragment.navController }
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.fragmentMainNavHost) as NavHostFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplash()
        setContentView(R.layout.activity_main)

        bottomNavigationView.setupWithNavController(navController)
    }

    private fun initSplash() {
        setTheme(R.style.Theme_SoccerT_Light)
        Thread.sleep(SPLASH_TIME)
    }

    companion object {
        const val SPLASH_TIME = 2000L
    }
}
