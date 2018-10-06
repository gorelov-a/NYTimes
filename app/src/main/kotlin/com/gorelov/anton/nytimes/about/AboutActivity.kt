package com.gorelov.anton.nytimes.about

import android.os.Bundle
import android.support.annotation.StringRes
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.di.DI
import kotlinx.android.synthetic.main.activity_about.*
import javax.inject.Inject


class AboutActivity : MvpAppCompatActivity(), AboutView {


    private val scope by lazy { DI.openAboutScope() }

    @Inject
    @InjectPresenter
    lateinit var aboutPresenter: AboutPresenter

    @ProvidePresenterTag(presenterClass = AboutPresenter::class)
    fun provideDialogPresenterTag(): String = "AboutPresenter"

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

    override fun showEmptyMessageToast() = Toast.makeText(this, R.string.empty_message, Toast.LENGTH_LONG).show()

    override fun setDisclaimer(text: String) {
        disclaimer.text = text
    }

    override fun showNoEmailClientToast() = showToast(R.string.no_email_client_error)

    override fun showNoBrowserToast() = showToast(R.string.no_browser_error)

    private fun showToast(@StringRes stringId: Int) = Toast.makeText(baseContext, stringId, Toast.LENGTH_LONG).show()
}
