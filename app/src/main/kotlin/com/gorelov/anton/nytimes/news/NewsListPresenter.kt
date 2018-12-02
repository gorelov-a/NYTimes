package com.gorelov.anton.nytimes.news

import com.arellomobile.mvp.InjectViewState
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.common.BasePresenter
import com.gorelov.anton.nytimes.common.DateFormatter
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.news.vm.NewsListItemConverter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class NewsListPresenter @Inject constructor(
        private val newsListInteractor: NewsListInteractor,
        val dateFormatter: DateFormatter
) : BasePresenter<NewsListView>() {

    override fun onFirstViewAttach() {
        viewState.changeProgressBarVisibility(true)
        disposable.add(newsListInteractor.getNewsList()
                .map { NewsListItemConverter.from(it, dateFormatter) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.changeProgressBarVisibility(false)
                    viewState.showNews(it)
                }, {
                    viewState.changeProgressBarVisibility(false)
                    viewState.showToast(R.string.load_news_error)
                }))
    }

    override fun onDestroy() {
        super.onDestroy()
        DI.closeNewsListScope()
    }
}