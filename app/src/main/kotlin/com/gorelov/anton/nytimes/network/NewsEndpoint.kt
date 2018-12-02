package com.gorelov.anton.nytimes.network

import com.gorelov.anton.nytimes.network.dto.NewsListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsEndpoint {

    @GET("/svc/topstories/v2/{category}.json")
    fun getNews(@Path("category") category: String): Observable<NewsListResponse>
}