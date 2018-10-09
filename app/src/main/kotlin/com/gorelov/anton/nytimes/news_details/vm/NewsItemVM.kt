package com.gorelov.anton.nytimes.news_details.vm

data class NewsItemVM(val id: Int,
                      val title: String,
                      val imageUrl: String,
                      val category: String,
                      val publishDate: CharSequence,
                      val content: String)