package com.gorelov.anton.nytimes.model

import java.util.*

data class NewsItem(
        val id: NewsItemId,
        val title: String?,
        val imageUrl: String?,
        val category: String?,
        val publishDate: Date,
        val previewText: String,
        val fullText: String?
)