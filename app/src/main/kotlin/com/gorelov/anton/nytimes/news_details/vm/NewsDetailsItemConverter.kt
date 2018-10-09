package com.gorelov.anton.nytimes.news_details.vm


import com.gorelov.anton.nytimes.common.DateUtils
import com.gorelov.anton.nytimes.model.NewsItem

object NewsDetailsItemConverter {

    fun from(news: List<NewsItem>, dateUtils: DateUtils): List<NewsDetailsItemVM> = news.mapTo(mutableListOf()) { from(it, dateUtils) }

    fun from(news: NewsItem, dateUtils: DateUtils): NewsDetailsItemVM = NewsDetailsItemVM(
            news.id.id,
            news.title,
            news.imageUrl,
            news.category.name,
            dateUtils.format(news.publishDate),
            news.fullText
    )
}