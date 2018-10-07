package com.gorelov.anton.nytimes.news

import com.arellomobile.mvp.MvpPresenter
import com.gorelov.anton.nytimes.di.DI
import javax.inject.Inject

class NewsListPresenter @Inject constructor() : MvpPresenter<NewsListView>() {

    override fun onDestroy() {
        super.onDestroy()
        DI.closeNewsListScope()
    }
}