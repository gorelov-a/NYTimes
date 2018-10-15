package com.gorelov.anton.nytimes.news

import com.gorelov.anton.nytimes.common.DataUtils
import javax.inject.Inject

class NewsListInteractor @Inject constructor(private val dataUtils: DataUtils) {

    fun getNewsList() = dataUtils.generateNews()
}