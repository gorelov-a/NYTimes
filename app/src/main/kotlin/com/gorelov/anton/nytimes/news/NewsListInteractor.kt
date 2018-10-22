package com.gorelov.anton.nytimes.news

import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.common.utils.DateFormatter
import com.gorelov.anton.nytimes.common.utils.ResourcesProvider
import com.gorelov.anton.nytimes.model.NewsItem
import com.gorelov.anton.nytimes.network.NewsEndpoint
import com.gorelov.anton.nytimes.network.NewsItemDtoConverter
import io.reactivex.Observable
import javax.inject.Inject

class NewsListInteractor @Inject constructor(
        private val newsEndpoint: NewsEndpoint,
        private val dateFormatter: DateFormatter,
        private val resourcesProvider: ResourcesProvider
) {

    fun getNewsCategories(): Observable<Array<String>> = Observable.fromArray(resourcesProvider.getStringArray(R.array.categories))

    fun getNewsList(category: String): Observable<List<NewsItem>> = newsEndpoint.getNews(category)
            .map { NewsItemDtoConverter.from(it.results, dateFormatter) }
}