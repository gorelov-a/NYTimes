package com.gorelov.anton.nytimes.about

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class AboutPresenter : MvpPresenter<AboutView>() {

    fun onTelegramButtonClick() {
        Log.d("AboutPresenter", "onTelegramButtonClick()")
    }

    fun onVkButtonClick() {

    }

    fun onEmailSendButtonClick(message: String) {

    }
}