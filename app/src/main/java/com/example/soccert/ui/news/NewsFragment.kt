package com.example.soccert.ui.news

import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.data.model.News
import com.example.soccert.databinding.FragmentNewsBinding
import com.example.soccert.ui.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<FragmentNewsBinding>() {
    private val newsAdapter = NewsAdapter(this::itemSelectedNews)

    override val layoutResource get() = R.layout.fragment_news
    override val viewModel by viewModel<NewsViewModel>()

    override fun initViews() {
        initToolbar()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbarNews)
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            newsVM = viewModel
            recyclerNews.adapter = newsAdapter
        }
    }

    override fun initActions() {
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.text_menu_search -> findNews(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun findNews(item: MenuItem) {
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.filterProduct(it) }
                return true
            }
        })
    }

    private fun itemSelectedNews(news: News) {
        turnOffInputKeyboard()
        val action = NewsFragmentDirections.actionNewsFragmentToNewsWebFragment(news.url)
        findNavController().navigate(action)
    }

    private fun turnOffInputKeyboard() {
        val keyboard =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
