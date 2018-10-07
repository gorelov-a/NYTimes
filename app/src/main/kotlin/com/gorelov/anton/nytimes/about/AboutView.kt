package com.gorelov.anton.nytimes.about

import android.content.Intent
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface AboutView : MvpView {

    fun showEmptyMessageToast()

    fun showNoEmailClientToast()

    fun showNoBrowserToast()

    fun startActivity(intent: Intent)

    @StateStrategyType(AddToEndStrategy::class)
    fun setDisclaimer(text: String)

}