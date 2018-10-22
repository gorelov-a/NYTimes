package com.gorelov.anton.nytimes.common.utils

import com.gorelov.anton.nytimes.common.AppConsts
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DateFormatter @Inject constructor() {

    fun format(date: Date): String {
        val df = SimpleDateFormat(AppConsts.newsDateFormat, Locale.getDefault())
        return df.format(date)
    }

    fun parse(date: String): Date {
        val df = SimpleDateFormat(AppConsts.newsServerDateFormat, Locale.getDefault())
        return df.parse(date)
    }
}