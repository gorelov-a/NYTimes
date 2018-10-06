package com.gorelov.anton.nytimes.about

import android.content.Intent
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface AboutView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showEmptyMessageToast()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showNoEmailClientToast()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showNoBrowserToast()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun startActivity(intent: Intent)

    fun setDisclaimer(text: String)

}