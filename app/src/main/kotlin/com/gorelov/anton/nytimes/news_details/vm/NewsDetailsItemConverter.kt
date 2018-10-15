package com.gorelov.anton.nytimes.news_details.vm


import com.gorelov.anton.nytimes.common.DateFormatter
import com.gorelov.anton.nytimes.model.NewsItem

object NewsDetailsItemConverter {

    fun from(news: List<NewsItem>, dateFormatter: DateFormatter): List<NewsDetailsItemVM> = news.mapTo(mutableListOf()) { from(it, dateFormatter) }

    fun from(news: NewsItem, dateFormatter: DateFormatter): NewsDetailsItemVM = NewsDetailsItemVM(
            news.id.id,
            news.title,
            news.imageUrl,
            news.category.name,
            dateFormatter.format(news.publishDate),
            news.fullText
    )
}