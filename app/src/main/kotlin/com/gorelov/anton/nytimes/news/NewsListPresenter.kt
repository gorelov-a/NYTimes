package com.gorelov.anton.nytimes.news

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gorelov.anton.nytimes.common.DateUtils
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.news.vm.NewsListItemConverter
import javax.inject.Inject

@InjectViewState
class NewsListPresenter @Inject constructor(private val newsListInteractor: NewsListInteractor, val dateUtils: DateUtils) : MvpPresenter<NewsListView>() {

    override fun onFirstViewAttach() {
        viewState.showNews(NewsListItemConverter.from(newsListInteractor.getNewsList(), dateUtils))
    }

    override fun onDestroy() {
        super.onDestroy()
        DI.closeNewsListScope()
    }
}