package com.gorelov.anton.nytimes.network.dto

import com.google.gson.annotations.SerializedName

data class NewsMultimediaDTO(@SerializedName("url") val url: String)