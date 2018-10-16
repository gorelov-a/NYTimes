package com.gorelov.anton.nytimes.news

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.gorelov.anton.nytimes.common.BaseView
import com.gorelov.anton.nytimes.news.vm.NewsListItemVM

@StateStrategyType(AddToEndStrategy::class)
interface NewsListView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showNews(news: List<NewsListItemVM>)

    fun showProgressBar()

    fun hideProgressBar()

}