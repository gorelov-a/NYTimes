package com.gorelov.anton.nytimes.news

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gorelov.anton.nytimes.common.DateFormatter
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.news.vm.NewsListItemConverter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@InjectViewState
class NewsListPresenter @Inject constructor(
        private val newsListInteractor: NewsListInteractor,
        val dateFormatter: DateFormatter
) : MvpPresenter<NewsListView>() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        viewState.showProgressBar()
        disposable.add(newsListInteractor.getNewsList()
                .map { NewsListItemConverter.from(it, dateFormatter) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    viewState.hideProgressBar()
                    viewState.showNews(it)
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        DI.closeNewsListScope()
    }
}