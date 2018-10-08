package com.gorelov.anton.nytimes.news_details

import com.arellomobile.mvp.MvpView

interface NewsDetailsView : MvpView {

    fun setActionBarTitle(title: String)
}