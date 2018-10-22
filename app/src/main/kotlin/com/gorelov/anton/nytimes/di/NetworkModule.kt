package com.gorelov.anton.nytimes.di

import com.gorelov.anton.nytimes.network.NewsApiProvider
import com.gorelov.anton.nytimes.network.NewsEndpoint
import toothpick.config.Module


class NetworkModule : Module() {
    init {
        bind(NewsEndpoint::class.java).toProvider(NewsApiProvider::class.java).providesSingletonInScope()
    }
}