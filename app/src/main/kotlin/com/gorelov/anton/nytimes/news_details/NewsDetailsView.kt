package com.gorelov.anton.nytimes.news_details

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.gorelov.anton.nytimes.common.BaseView
import com.gorelov.anton.nytimes.news_details.vm.NewsDetailsItemVM

@StateStrategyType(AddToEndStrategy::class)
interface NewsDetailsView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showNewsItem(newsDetailsItemVM: NewsDetailsItemVM)

    fun showProgressBar()

    fun hideProgressBar()
}