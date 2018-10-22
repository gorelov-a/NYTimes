package com.gorelov.anton.nytimes.news

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.gorelov.anton.nytimes.common.BaseView
import com.gorelov.anton.nytimes.news.vm.NewsListItemVM

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewsListView : BaseView {

    fun showNews(news: List<NewsListItemVM>)

    fun setRefreshing(isRefreshing: Boolean)

    fun setCategoryListDialogVisibility(visibility: Boolean)

    fun setCategory(category: String)
}