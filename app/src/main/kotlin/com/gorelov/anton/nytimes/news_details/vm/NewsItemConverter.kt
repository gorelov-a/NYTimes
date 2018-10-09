package com.gorelov.anton.nytimes.news_details.vm


import com.gorelov.anton.nytimes.common.DateUtils
import com.gorelov.anton.nytimes.model.NewsItem

object NewsItemConverter {

    fun from(news: List<NewsItem>, dateUtils: DateUtils): List<NewsItemVM> = news.mapTo(mutableListOf()) { from(it, dateUtils) }

    fun from(news: NewsItem, dateUtils: DateUtils): NewsItemVM = NewsItemVM(
            news.id.id,
            news.title,
            news.imageUrl,
            news.category.name,
            dateUtils.format(news.publishDate),
            news.fullText
    )
}