package com.example.soccert.ui.more

import android.app.AlertDialog
import android.content.Intent
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.databinding.FragmentMoreBinding
import com.example.soccert.ui.home.HomeViewModel
import com.example.soccert.utils.checkTheme
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MoreFragment : BaseFragment<FragmentMoreBinding>() {

    override val layoutResource get() = R.layout.fragment_more
    override val viewModel by sharedViewModel<HomeViewModel>()

    override fun initViews() {
        checkTheme()
    }

    private fun checkTheme() {
        binding.switchChangeTheme.isChecked =
            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
    }

    override fun initData() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initActions() {
        binding.textShareApp.setOnClickListener {
            shareApp()
        }

        binding.textInfoApp.setOnClickListener {
            showInfoApp()
        }

        binding.textNotification.setOnClickListener {
            showNotificationManager()
        }

        binding.switchChangeTheme.checkTheme()
    }

    private fun shareApp() {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, getString(R.string.text_extra_intent_share_app))
            type = TYPE_INTENT
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun showInfoApp() {
        AlertDialog.Builder(context)
            .setView(R.layout.view_info_app)
            .show()
    }

    private fun showNotificationManager() {
        val action = MoreFragmentDirections.actionMoreFragmentToNotificationFragment()
        findNavController().navigate(action)
    }

    companion object {
        const val TYPE_INTENT = "text/plan"
    }
}
