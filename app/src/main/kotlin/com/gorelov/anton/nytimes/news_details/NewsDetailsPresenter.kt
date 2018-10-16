package com.gorelov.anton.nytimes.news_details

import com.arellomobile.mvp.InjectViewState
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.common.BasePresenter
import com.gorelov.anton.nytimes.common.DateFormatter
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.model.NewsItemId
import com.gorelov.anton.nytimes.news_details.vm.NewsDetailsItemConverter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class NewsDetailsPresenter @Inject constructor(
        private val newsId: NewsItemId,
        private val interactor: NewsDetailsInteractor,
        private val dateFormatter: DateFormatter
) : BasePresenter<NewsDetailsView>() {

    override fun onFirstViewAttach() {
        viewState.showProgressBar()
        disposable.add(interactor.getNewsItemById(newsId)
                .map { NewsDetailsItemConverter.from(it, dateFormatter) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.hideProgressBar()
                    viewState.showNewsItem(it)
                }) {
                    viewState.showToast(R.string.no_email_client_error)
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        DI.closeNewsDetailsScope()
    }
}