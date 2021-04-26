package com.example.soccert.ui.news

import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.databinding.FragmentNewsWebBinding
import com.example.soccert.utils.ToastType
import com.example.soccert.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsWebFragment : BaseFragment<FragmentNewsWebBinding>() {
    private val args: NewsWebFragmentArgs by navArgs()

    override val layoutResource get() = R.layout.fragment_news_web
    override val viewModel by viewModel<NewsViewModel>()

    override fun initViews() {
        binding.webNewsSport.apply {
            webViewClient = WebViewClient()
            loadUrl(args.link)
        }
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun initActions() {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            context?.showToast(ToastType.Error, it)
        })
    }
}
