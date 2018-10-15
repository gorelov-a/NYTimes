package com.gorelov.anton.nytimes.news.vm

data class NewsListItemVM(val id: Int,
                          val title: String,
                          val imageUrl: String,
                          val category: String,
                          val publishDate: CharSequence,
                          val previewText: String)