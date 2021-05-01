package com.example.soccert.ui.adapter

import android.graphics.text.LineBreaker
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.News
import com.example.soccert.databinding.ItemNewsBinding

class NewsAdapter(
    private val onItemClicked: (News) -> Unit
) : BaseAdapter<News, NewsAdapter.NewsViewHolder>(News.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_news, parent, false
            ),
            onItemClicked
        )

    class NewsViewHolder(
        private val itemNewsBinding: ItemNewsBinding,
        onItemClicked: (News) -> Unit
    ) : BaseViewHolder<News>(itemNewsBinding, onItemClicked) {

        override fun onBind(itemData: News) {
            super.onBind(itemData)
            itemNewsBinding.news = itemData
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                itemNewsBinding.textNewsDescription.justificationMode =
                    LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            }
        }
    }
}
