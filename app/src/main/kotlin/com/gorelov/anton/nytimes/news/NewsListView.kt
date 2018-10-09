package com.gorelov.anton.nytimes.news

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.gorelov.anton.nytimes.news.vm.NewsListItemVM

@StateStrategyType(OneExecutionStateStrategy::class)
interface NewsListView : MvpView {

    fun showNews(news: List<NewsListItemVM>)

}