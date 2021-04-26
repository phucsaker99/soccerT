package com.example.soccert.ui.news

import com.example.soccert.base.RxViewModel
import com.example.soccert.data.repository.SearchNewsRepository

class NewsViewModel(
    private val searchNewsRepository: SearchNewsRepository
) : RxViewModel()
