package com.gorelov.anton.nytimes.common

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DateFormatter @Inject constructor() {

    fun format(date: Date): CharSequence {
        val df = SimpleDateFormat(AppConsts.newsDateFormat, Locale.getDefault())
        return df.format(date)
    }
}