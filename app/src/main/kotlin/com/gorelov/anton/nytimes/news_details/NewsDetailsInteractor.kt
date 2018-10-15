package com.gorelov.anton.nytimes.news_details

import com.gorelov.anton.nytimes.common.DataUtils
import com.gorelov.anton.nytimes.model.NewsItem
import com.gorelov.anton.nytimes.model.NewsItemId
import javax.inject.Inject

class NewsDetailsInteractor @Inject constructor(private val dataUtils: DataUtils) {

    fun getNewsItemById(newsId: NewsItemId): NewsItem? = dataUtils.getNewsItemById(newsId)
}