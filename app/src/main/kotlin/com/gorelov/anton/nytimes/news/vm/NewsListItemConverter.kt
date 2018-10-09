package com.gorelov.anton.nytimes.news.vm

import com.gorelov.anton.nytimes.common.DateUtils
import com.gorelov.anton.nytimes.model.NewsItem

object NewsListItemConverter {

    fun from(news: List<NewsItem>, dateUtils: DateUtils): List<NewsListItemVM> = news.mapTo(mutableListOf()) { from(it, dateUtils) }

    fun from(news: NewsItem, dateUtils: DateUtils): NewsListItemVM = NewsListItemVM(
            news.id.id,
            news.title,
            news.imageUrl,
            news.category.name,
            dateUtils.format(news.publishDate),
            news.previewText
    )
}