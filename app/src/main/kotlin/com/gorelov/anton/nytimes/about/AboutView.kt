package com.gorelov.anton.nytimes.about

import com.arellomobile.mvp.MvpView

interface AboutView : MvpView {

    fun showEmptyMessageToast()

    fun checkAndOpenMail(message: String)

    fun openTelegramChat()

    fun openVKPage()

    fun openWhatsAppChat()

    fun setDisclaimer(text: String)

}