package com.gorelov.anton.nytimes.network

import com.gorelov.anton.nytimes.network.interceptor.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class NewsApiProvider @Inject constructor() : Provider<NewsEndpoint> {

    override fun get(): NewsEndpoint {
        val httpClient = buildOkHttpClient()
        val retrofit = buildRetrofitClient(httpClient)

        return retrofit.create(NewsEndpoint::class.java)
    }

    private fun buildOkHttpClient(): OkHttpClient {
        val networkLogInterceptor = HttpLoggingInterceptor()
        networkLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        return OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor(NetworkConsts.apiKey))
                .addInterceptor(networkLogInterceptor)
                .connectTimeout(NetworkConsts.timeoutInSeconds, TimeUnit.SECONDS)
                .writeTimeout(NetworkConsts.timeoutInSeconds, TimeUnit.SECONDS)
                .readTimeout(NetworkConsts.timeoutInSeconds, TimeUnit.SECONDS)
                .build()
    }

    private fun buildRetrofitClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(NetworkConsts.baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}