package com.gorelov.anton.nytimes.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response


class ApiKeyInterceptor constructor(private val apiKey: String) : Interceptor {

    companion object {
        private const val PARAM_API_KEY = "api_key"
    }

    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
            chain.request()
                    .newBuilder()
                    .addHeader(PARAM_API_KEY, apiKey)
                    .build())
}