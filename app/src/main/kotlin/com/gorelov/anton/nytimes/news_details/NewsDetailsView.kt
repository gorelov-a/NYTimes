package com.gorelov.anton.nytimes.news_details

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.gorelov.anton.nytimes.news_details.vm.NewsItemVM

@StateStrategyType(OneExecutionStateStrategy::class)
interface NewsDetailsView : MvpView {

    fun showNewsItem(newsItemVM: NewsItemVM)

    fun showToast(@StringRes stringId: Int)
}