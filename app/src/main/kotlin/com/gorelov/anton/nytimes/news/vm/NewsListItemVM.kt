package com.gorelov.anton.nytimes.news.vm

data class NewsListItemVM(val id: String,
                          val title: String?,
                          val imageUrl: String?,
                          val category: String?,
                          val publishDate: String,
                          val previewText: String)