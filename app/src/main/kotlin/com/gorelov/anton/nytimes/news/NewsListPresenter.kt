package com.gorelov.anton.nytimes.news

import com.arellomobile.mvp.InjectViewState
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.common.BasePresenter
import com.gorelov.anton.nytimes.common.utils.DateFormatter
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.news.vm.NewsListItemConverter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class NewsListPresenter @Inject constructor(
        private val newsListInteractor: NewsListInteractor,
        private val dateFormatter: DateFormatter
) : BasePresenter<NewsListView>() {

    private lateinit var categories: Array<String>

    override fun onFirstViewAttach() {
        viewState.setRefreshing(true)
        disposable.add(
                newsListInteractor
                        .getNewsCategories()
                        .flatMap {
                            categories = it
                            viewState.setCategory(categories[0])
                            Observable.fromArray(categories[0])
                        }
                        .flatMap { newsListInteractor.getNewsList(it) }
                        .map { NewsListItemConverter.from(it, dateFormatter) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            viewState.setRefreshing(false)
                            viewState.showNews(it)
                        }, {
                            viewState.setRefreshing(false)
                            viewState.showToast(R.string.load_news_error)
                        }))
    }

    fun onRefresh() = onFirstViewAttach()

    fun onCategoryClick() {
        viewState.setCategoryListDialogVisibility(true)
    }

    fun onCategoryItemClick(position: Int) {
        viewState.setRefreshing(true)
        viewState.setCategoryListDialogVisibility(false)
        disposable.add(
                newsListInteractor.getNewsList(categories[position])
                        .map { NewsListItemConverter.from(it, dateFormatter) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            viewState.setRefreshing(false)
                            viewState.showNews(it)
                        }, {
                            viewState.setRefreshing(false)
                            viewState.showToast(R.string.load_news_error)
                        }))
    }

    override fun onDestroy() {
        super.onDestroy()
        DI.closeNewsListScope()
    }
}