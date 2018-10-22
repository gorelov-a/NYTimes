package com.gorelov.anton.nytimes.network.dto

import com.google.gson.annotations.SerializedName

data class NewsListResponse(@SerializedName("results") val results: List<NewsItemDTO>)