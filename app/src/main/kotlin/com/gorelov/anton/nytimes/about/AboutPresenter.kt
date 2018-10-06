package com.gorelov.anton.nytimes.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.common.ResourcesProvider
import com.gorelov.anton.nytimes.di.DI
import java.util.*
import javax.inject.Inject

@InjectViewState
class AboutPresenter @Inject constructor(private val applicationContext: Context, private val resourcesProvider: ResourcesProvider) : MvpPresenter<AboutView>() {

    fun onTelegramButtonClick() = checkIntentAndStartActivity(Intent(Intent.ACTION_VIEW, Uri.parse(AboutConsts.telegramUrl)), viewState::showNoBrowserToast)

    fun onVkButtonClick() = checkIntentAndStartActivity(Intent(Intent.ACTION_VIEW, Uri.parse(AboutConsts.vkUrl)), viewState::showNoBrowserToast)

    fun onWhatsAppButtonClick() = checkIntentAndStartActivity(Intent(Intent.ACTION_VIEW, Uri.parse(AboutConsts.whatsAppUrl)), viewState::showNoBrowserToast)

    fun onEmailSendButtonClick(message: String) {
        if (message.isBlank()) {
            viewState.showEmptyMessageToast()
        } else {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${AboutConsts.email}")
                putExtra(Intent.EXTRA_TEXT, message)
            }
            checkIntentAndStartActivity(intent, viewState::showNoEmailClientToast)
        }
    }

    override fun onFirstViewAttach() {
        viewState.setDisclaimer(resourcesProvider.getString(R.string.disclaimer, getCurrentYear()))
    }

    override fun onDestroy() {
        super.onDestroy()
        DI.closeAboutScope()
    }

    private fun checkIntentAndStartActivity(intent: Intent, error: () -> Unit) = when {
        intent.resolveActivity(applicationContext.packageManager) != null -> viewState.startActivity(intent)
        else -> error()
    }

    private fun getCurrentYear(): Int = Calendar.getInstance().get(Calendar.YEAR)
}