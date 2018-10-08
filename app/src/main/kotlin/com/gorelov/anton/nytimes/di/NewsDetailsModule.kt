package com.gorelov.anton.nytimes.di

import toothpick.config.Module

class NewsDetailsModule(newsId: Int) : Module() {
    init {
        bind(Int::class.java).toInstance(newsId)
    }
}