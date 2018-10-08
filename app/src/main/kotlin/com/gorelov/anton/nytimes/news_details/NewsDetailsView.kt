package com.gorelov.anton.nytimes.news_details

import android.graphics.drawable.Drawable
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface NewsDetailsView : MvpView {

    fun setActionBarCategory(category: String)

    fun setTitle(title: String)

    fun setContent(content: String)

    fun setDate(string: String)

    fun setHeadingImage(drawable: Drawable)

}