package com.gorelov.anton.nytimes.about

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gorelov.anton.nytimes.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : MvpAppCompatActivity(), AboutView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var aboutPresenter: AboutPresenter

    @ProvidePresenterTag(presenterClass = AboutPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "AboutPresenter"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideAboutPresenter(): AboutPresenter = AboutPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        send_message_button.setOnClickListener {
            aboutPresenter.onEmailSendButtonClick(message_input.text.toString())
        }

        icon_telegram.setOnClickListener {
            aboutPresenter.onTelegramButtonClick()
        }

        icon_vk.setOnClickListener {
            aboutPresenter.onVkButtonClick()
        }
    }
}
