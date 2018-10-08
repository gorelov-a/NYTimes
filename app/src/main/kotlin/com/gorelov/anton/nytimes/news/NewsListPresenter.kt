package com.gorelov.anton.nytimes.news

import com.arellomobile.mvp.MvpPresenter
import com.gorelov.anton.nytimes.common.DataUtils
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.model.NewsItem
import javax.inject.Inject

class NewsListPresenter @Inject constructor() : MvpPresenter<NewsListView>() {

    fun getNewList(): List<NewsItem> {
        return DataUtils().generateNews()
    }

    override fun onDestroy() {
        super.onDestroy()
        DI.closeNewsListScope()
    }
}