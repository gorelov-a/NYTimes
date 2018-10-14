package com.gorelov.anton.nytimes.news_details

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.gorelov.anton.nytimes.news_details.vm.NewsDetailsItemVM

@StateStrategyType(AddToEndStrategy::class)
interface NewsDetailsView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showNewsItem(newsDetailsItemVM: NewsDetailsItemVM)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(@StringRes stringId: Int)

    fun showProgressBar()

    fun hideProgressBar()
}