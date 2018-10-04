package com.gorelov.anton.nytimes.about

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import java.util.*

@InjectViewState
class AboutPresenter : MvpPresenter<AboutView>() {

    fun onTelegramButtonClick() = viewState.openTelegramChat()

    fun onVkButtonClick() = viewState.openVKPage()

    fun onWhatsAppButtonClick() = viewState.openWhatsAppChat()

    fun onEmailSendButtonClick(message: String) {
        if (message.isBlank()) {
            viewState.showEmptyMessageToast()
        } else {
            viewState.checkAndOpenMail(message)
        }
    }

    fun getCurrentYear(): Int = Calendar.getInstance().get(Calendar.YEAR)
}