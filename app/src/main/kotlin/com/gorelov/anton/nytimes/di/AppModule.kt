package com.gorelov.anton.nytimes.di

import android.content.Context
import com.gorelov.anton.nytimes.common.DataUtils
import com.gorelov.anton.nytimes.common.DateFormatter
import com.gorelov.anton.nytimes.common.ResourcesProvider
import toothpick.config.Module

class AppModule(applicationContext: Context) : Module() {
    init {
        bind(Context::class.java).toInstance(applicationContext)
        bind(ResourcesProvider::class.java).to(ResourcesProvider::class.java).singletonInScope()
        bind(DateFormatter::class.java).to(DateFormatter::class.java).singletonInScope()
        bind(DataUtils::class.java).to(DataUtils::class.java).singletonInScope()
    }

}