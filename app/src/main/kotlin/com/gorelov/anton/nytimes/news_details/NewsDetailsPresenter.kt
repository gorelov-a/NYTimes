package com.gorelov.anton.nytimes.news_details

import com.arellomobile.mvp.InjectViewState
import com.gorelov.anton.nytimes.common.BasePresenter
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.model.NewsItemId
import javax.inject.Inject

@InjectViewState
class NewsDetailsPresenter @Inject constructor(
        private val newsId: NewsItemId
) : BasePresenter<NewsDetailsView>() {

    override fun onFirstViewAttach() {
        viewState.openUrl(newsId.url)
    }

    override fun onDestroy() {
        super.onDestroy()
        DI.closeNewsDetailsScope()
    }
}