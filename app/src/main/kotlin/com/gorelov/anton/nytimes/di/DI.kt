package com.gorelov.anton.nytimes.di

import android.content.Context
import com.gorelov.anton.nytimes.BuildConfig
import com.gorelov.anton.nytimes.model.NewsItemId
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator

object DI {

    private const val APP_SCOPE = "app_scope"
    private const val ABOUT_SCOPE = "about_scope"
    private const val NEWS_LIST_SCOPE = "news_list_scope"
    private const val NEWS_DETAILS_SCOPE = "news_details_scope"

    fun init(applicationContext: Context) {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
            MemberInjectorRegistryLocator.setRootRegistry(com.gorelov.anton.nytimes.MemberInjectorRegistry())
            FactoryRegistryLocator.setRootRegistry(com.gorelov.anton.nytimes.FactoryRegistry())
        }

        Toothpick.openScopes(APP_SCOPE).apply {
            installModules(AppModule(applicationContext))
        }
    }

    fun openAboutScope(): Scope = Toothpick.openScopes(APP_SCOPE, ABOUT_SCOPE)

    fun closeAboutScope() = Toothpick.closeScope(ABOUT_SCOPE)

    fun openNewsListScope(): Scope = Toothpick.openScopes(APP_SCOPE, NEWS_LIST_SCOPE)

    fun closeNewsListScope() = Toothpick.closeScope(NEWS_LIST_SCOPE)

    fun openNewsDetailsScope(newsId: NewsItemId): Scope = Toothpick.openScopes(APP_SCOPE, NEWS_DETAILS_SCOPE).apply {
        installModules(NewsDetailsModule(newsId))
    }

    fun closeNewsDetailsScope() = Toothpick.closeScope(NEWS_DETAILS_SCOPE)
}