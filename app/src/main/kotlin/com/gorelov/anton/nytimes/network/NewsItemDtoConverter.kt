package com.gorelov.anton.nytimes.network

import com.gorelov.anton.nytimes.common.utils.DateFormatter
import com.gorelov.anton.nytimes.model.NewsItem
import com.gorelov.anton.nytimes.model.NewsItemId
import com.gorelov.anton.nytimes.network.dto.NewsItemDTO

object NewsItemDtoConverter {
    fun from(news: List<NewsItemDTO>, dateFormatter: DateFormatter): List<NewsItem> = news.mapTo(mutableListOf()) { from(it, dateFormatter) }

    fun from(news: NewsItemDTO, dateFormatter: DateFormatter): NewsItem = NewsItem(
            NewsItemId(news.url),
            news.title,
            if (!news.multimedia.isEmpty()) news.multimedia[0]?.url else "",
            news.subsection,
            dateFormatter.parse(news.publishedDate),
            news.abstract,
            "")
}