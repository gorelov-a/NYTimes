package com.gorelov.anton.nytimes.news

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.gorelov.anton.nytimes.news.vm.NewsListItemVM

interface NewsListView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showNews(news: List<NewsListItemVM>)

}