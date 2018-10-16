package com.gorelov.anton.nytimes.about

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.gorelov.anton.nytimes.common.BaseView

@StateStrategyType(OneExecutionStateStrategy::class)
interface AboutView : BaseView {

    fun checkAndOpenMail(message: String)

    fun openTelegramChat()

    fun openVKPage()

    fun openWhatsAppChat()

    @StateStrategyType(AddToEndStrategy::class)
    fun setDisclaimer(text: String)
}