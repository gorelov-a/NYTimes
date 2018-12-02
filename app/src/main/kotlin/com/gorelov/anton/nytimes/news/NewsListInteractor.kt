package com.gorelov.anton.nytimes.news

import com.gorelov.anton.nytimes.common.DataUtils
import com.gorelov.anton.nytimes.model.NewsItem
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class NewsListInteractor @Inject constructor(private val dataUtils: DataUtils) {

    fun getNewsList(): Observable<ArrayList<NewsItem>> = dataUtils.generateNews()
}