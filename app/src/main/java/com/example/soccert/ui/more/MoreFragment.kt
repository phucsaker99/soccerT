package com.example.soccert.ui.more

import android.app.AlertDialog
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.databinding.FragmentMoreBinding
import com.example.soccert.ui.home.HomeViewModel
import com.example.soccert.ui.main.MainViewModel
import com.example.soccert.utils.LanguageConst
import com.example.soccert.utils.checkTheme
import kotlinx.android.synthetic.main.fragment_more.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MoreFragment : BaseFragment<FragmentMoreBinding>() {

    override val layoutResource get() = R.layout.fragment_more
    override val viewModel by sharedViewModel<HomeViewModel>()

    override fun initViews() {
        initToolbar()
        checkTheme()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbarMore)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_language, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_english -> activity?.getViewModel<MainViewModel>()
                ?.setLanguage(LanguageConst.LANGUAGE_KEY_ENGLISH)
            R.id.menu_vietnamese -> activity?.getViewModel<MainViewModel>()
                ?.setLanguage(LanguageConst.LANGUAGE_KEY_VIETNAMESE)
        }
        return super.onOptionsItemSelected(item)
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
