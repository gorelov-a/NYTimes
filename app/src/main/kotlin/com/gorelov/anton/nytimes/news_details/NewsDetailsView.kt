package com.gorelov.anton.nytimes.news_details

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.gorelov.anton.nytimes.common.BaseView

@StateStrategyType(AddToEndStrategy::class)
interface NewsDetailsView : BaseView {

    fun openUrl(url: String)
}