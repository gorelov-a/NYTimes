package com.gorelov.anton.nytimes.model

import java.util.*

data class NewsItem(
        val id: NewsItemId,
        val title: String,
        val imageUrl: String,
        val category: Category,
        val publishDate: Date,
        val previewText: String,
        val fullText: String
)

data class NewsItemId(val id: Int)