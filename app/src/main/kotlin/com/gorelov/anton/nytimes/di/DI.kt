package com.gorelov.anton.nytimes.di

import android.content.Context
import com.gorelov.anton.nytimes.BuildConfig
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator

object DI {

    private val APP_SCOPE = "app_scope"
    private val ABOUT_SCOPE = "about_scope"

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
}