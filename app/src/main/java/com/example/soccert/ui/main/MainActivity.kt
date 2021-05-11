package com.example.soccert.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.soccert.R
import com.example.soccert.utils.AlarmConst.EXTRA_OPEN_NOTIFICATION
import com.example.soccert.utils.NetworkUtil
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private val navController by lazy { navHostFragment.navController }
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.fragmentMainNavHost) as NavHostFragment }
    private val viewModel by viewModel<MainViewModel>()
    private var subcription: Disposable? = null
    private var isConnectionInternet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        initTheme()
        super.onCreate(savedInstanceState)
        ViewModelProviders.of(this).get(viewModel.javaClass)
        isConnectionInternet = NetworkUtil.isConnection(this)
        if (!isConnectionInternet) {
            showDialogCheckInternet()
            return
        }
        updateLanguage(viewModel.language.value)
        setContentView(R.layout.activity_main)

        initAction()

        val navOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_right)
            .setPopExitAnim(R.anim.slide_out_left)
            .setPopUpTo(navController.graph.startDestination, false)
            .build()

        bottomNavigationView.apply {
            setupWithNavController(navController)
            setOnNavigationItemSelectedListener {
                if (it.itemId != bottomNavigationView.selectedItemId) {
                    navController.navigate(it.itemId, null, navOptions)
                }
                true
            }
        }
    }

    private fun initAction() {
        viewModel.language.observe(this, Observer {
            updateLanguage(it)
        })

        viewModel.languageChange.observe(this, Observer {
            updateLanguage(it)
            resetApp()
        })
    }

    private fun resetApp() {
        finish()
        startActivity(intent)
    }

    private fun updateLanguage(language: String?) {
        language?.let {
            val locale = Locale(it)
            Locale.setDefault(locale)
            val displayMetrics = resources.displayMetrics
            val configuration = resources.configuration
            configuration.locale = locale
            resources.updateConfiguration(configuration, displayMetrics)
        }
    }

    private fun initTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.Theme_SoccerT_Dark)
        } else {
            setTheme(R.style.Theme_SoccerT_Light)
        }
    }

    private fun showDialogCheckInternet() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.title_internet_notification))
            .setMessage(getString(R.string.msg_notification_check_internet))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.action_ok)) { _, _ ->
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                finish()
            }
            .setNegativeButton(getString(R.string.action_cancel)) { _, _ ->
                finish()
            }.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        subcription?.dispose()
    }

    companion object{
        fun getIntentFromNotification(context: Context) =
            Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra(EXTRA_OPEN_NOTIFICATION, true)
            }
    }
}
