package com.gorelov.anton.nytimes.news.vm

import com.gorelov.anton.nytimes.common.DateFormatter
import com.gorelov.anton.nytimes.model.NewsItem

object NewsListItemConverter {

    fun from(news: List<NewsItem>, dateFormatter: DateFormatter): List<NewsListItemVM> = news.mapTo(mutableListOf()) { from(it, dateFormatter) }

    fun from(news: NewsItem, dateFormatter: DateFormatter): NewsListItemVM = NewsListItemVM(
            news.id.id,
            news.title,
            news.imageUrl,
            news.category.name,
            dateFormatter.format(news.publishDate),
            news.previewText
    )
}