package com.gorelov.anton.nytimes.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.common.BaseActivity
import com.gorelov.anton.nytimes.di.DI
import kotlinx.android.synthetic.main.activity_about.*


class AboutActivity : BaseActivity(), AboutView {


    private val scope by lazy { DI.openAboutScope() }

    @InjectPresenter
    lateinit var aboutPresenter: AboutPresenter

    @ProvidePresenter
    fun provideAboutPresenter(): AboutPresenter = scope.getInstance(AboutPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        with(aboutPresenter) {
            send_message_button.setOnClickListener { onEmailSendButtonClick(message_input.text.toString()) }
            icon_telegram.setOnClickListener { onTelegramButtonClick() }
            icon_vk.setOnClickListener { onVkButtonClick() }
            icon_whatsapp.setOnClickListener { onWhatsAppButtonClick() }
        }
    }

    override fun checkAndOpenMail(message: String) {
        with(Intent(Intent.ACTION_SENDTO)) {
            data = Uri.parse("mailto:${AboutConsts.email}")
            putExtra(Intent.EXTRA_TEXT, message)
            when {
                resolveActivity(packageManager) != null -> startActivity(this)
                else -> aboutPresenter.onNoEmailClientError()
            }
        }
    }

    override fun openTelegramChat() = openUri(AboutConsts.telegramUrl)

    override fun openVKPage() = openUri(AboutConsts.vkUrl)

    override fun openWhatsAppChat() = openUri(AboutConsts.whatsAppUrl)

    override fun setDisclaimer(text: String) {
        disclaimer.text = text
    }

    private fun openUri(uri: String) {
        with(Intent(Intent.ACTION_VIEW, Uri.parse(uri))) {
            when {
                resolveActivity(packageManager) != null -> startActivity(this)
                else -> aboutPresenter.onNoBrowserError()
            }
        }
    }
}
