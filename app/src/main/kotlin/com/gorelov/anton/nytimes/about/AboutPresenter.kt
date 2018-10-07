package com.gorelov.anton.nytimes.about

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.common.ResourcesProvider
import com.gorelov.anton.nytimes.di.DI
import java.util.*
import javax.inject.Inject

@InjectViewState
class AboutPresenter @Inject constructor(private val resourcesProvider: ResourcesProvider) : MvpPresenter<AboutView>() {

    fun onTelegramButtonClick() = viewState.openTelegramChat()

    fun onVkButtonClick() = viewState.openVKPage()

    fun onWhatsAppButtonClick() = viewState.openWhatsAppChat()

    fun onEmailSendButtonClick(message: String) {
        if (message.isBlank()) {
            viewState.showToast(R.string.empty_message)
        } else {
            viewState.checkAndOpenMail(message)
        }
    }

    fun onNoBrowserError() = viewState.showToast(R.string.no_browser_error)

    fun onNoEmailClientError() = viewState.showToast(R.string.no_email_client_error)

    override fun onFirstViewAttach() {
        viewState.setDisclaimer(resourcesProvider.getString(R.string.disclaimer, getCurrentYear()))
    }

    override fun onDestroy() {
        super.onDestroy()
        DI.closeAboutScope()
    }

    private fun getCurrentYear(): Int = Calendar.getInstance().get(Calendar.YEAR)
}