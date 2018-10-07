package com.gorelov.anton.nytimes.about

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface AboutView : MvpView {

    fun checkAndOpenMail(message: String)

    fun openTelegramChat()

    fun openVKPage()

    fun openWhatsAppChat()

    fun showToast(@StringRes stringId: Int)

    @StateStrategyType(AddToEndStrategy::class)
    fun setDisclaimer(text: String)
}