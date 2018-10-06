package com.gorelov.anton.nytimes

import android.app.Application
import com.gorelov.anton.nytimes.di.DI


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DI.init(this)
    }
}