package com.example.soccert.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.soccert.R
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private val navController by lazy { navHostFragment.navController }
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.fragmentMainNavHost) as NavHostFragment }
    private var subcription: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplash()
        initTheme()
        setContentView(R.layout.activity_main)

        bottomNavigationView.setupWithNavController(navController)
    }

    private fun initSplash() {
        subcription = Observable.just(true).delay(SPLASH_TIME, TimeUnit.MILLISECONDS)
            .doOnNext { setTheme(R.style.Theme_SoccerT_Light) }
            .subscribe()
    }

    private fun initTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.Theme_SoccerT_Dark)
        } else {
            setTheme(R.style.Theme_SoccerT_Light)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        subcription?.dispose()
    }

    companion object {
        const val SPLASH_TIME = 2000L
    }
}
