package com.gorelov.anton.nytimes.di

import com.gorelov.anton.nytimes.model.NewsItemId
import toothpick.config.Module

class NewsDetailsModule(newsId: NewsItemId) : Module() {
    init {
        bind(NewsItemId::class.java).toInstance(newsId)
    }
}