package com.gorelov.anton.nytimes.about

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface AboutView : MvpView {

    @StateStrategyType(value = SkipStrategy::class)
    fun showEmptyMessageToast()

    @StateStrategyType(value = SkipStrategy::class)
    fun checkAndOpenMail(message: String)

    @StateStrategyType(value = SkipStrategy::class)
    fun openTelegramChat()

    @StateStrategyType(value = SkipStrategy::class)
    fun openVKPage()

    @StateStrategyType(value = SkipStrategy::class)
    fun openWhatsAppChat()

}