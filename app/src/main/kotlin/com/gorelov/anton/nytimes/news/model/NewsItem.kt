package com.gorelov.anton.nytimes.news.model

import java.util.*

class NewsItem(
        val title: String,
        val imageUrl: String,
        val category: Category,
        val publishDate: Date,
        val previewText: String,
        val fullText: String
)