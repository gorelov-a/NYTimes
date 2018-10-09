package com.gorelov.anton.nytimes.news_details

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.common.DateUtils
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.model.NewsItemId
import com.gorelov.anton.nytimes.news_details.vm.NewsDetailsItemConverter
import javax.inject.Inject

@InjectViewState
class NewsDetailsPresenter @Inject constructor(
        private val newsId: NewsItemId,
        private val interactor: NewsDetailsInteractor,
        private val dateUtils: DateUtils
) : MvpPresenter<NewsDetailsView>() {

    override fun onFirstViewAttach() {
        val newsItem = interactor.getNewsItemById(newsId)

        if (newsItem != null) {
            val newsItemVM = NewsDetailsItemConverter.from(newsItem, dateUtils)
            viewState.showNewsItem(newsItemVM)
        } else {
            viewState.showToast(R.string.no_email_client_error)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DI.closeNewsDetailsScope()
    }
}