package com.gorelov.anton.nytimes.network

import com.gorelov.anton.nytimes.BuildConfig
import com.gorelov.anton.nytimes.network.interceptor.ApiKeyInterceptor
import io.reactivex.schedulers.Schedulers
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

        val okHttpClienBuilder = OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor(NetworkConsts.apiKey))
                .connectTimeout(NetworkConsts.timeoutInSeconds, TimeUnit.SECONDS)
                .writeTimeout(NetworkConsts.timeoutInSeconds, TimeUnit.SECONDS)
                .readTimeout(NetworkConsts.timeoutInSeconds, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpClienBuilder.addInterceptor(
                    HttpLoggingInterceptor()
                            .apply { level = HttpLoggingInterceptor.Level.BODY })
        }

        return okHttpClienBuilder.build()
    }

    private fun buildRetrofitClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(NetworkConsts.baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}