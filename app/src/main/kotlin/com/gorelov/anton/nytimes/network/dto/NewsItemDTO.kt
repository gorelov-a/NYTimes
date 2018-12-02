package com.gorelov.anton.nytimes.network.dto

import com.google.gson.annotations.SerializedName

data class NewsItemDTO(
        @SerializedName("subsection") val subsection: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("abstract") val abstract: String,
        @SerializedName("url") val url: String,
        @SerializedName("published_date") val publishedDate: String,
        @SerializedName("multimedia") val multimedia: List<NewsMultimediaDTO?>)