package com.gorelov.anton.nytimes.common

import android.content.Context
import android.text.format.DateUtils
import java.util.*
import javax.inject.Inject

class DateUtils @Inject constructor(private val context: Context) {

    fun format(date: Date): CharSequence {
        return DateUtils.getRelativeDateTimeString(
                context,
                date.time,
                DateUtils.MINUTE_IN_MILLIS,
                DateUtils.DAY_IN_MILLIS,
                DateUtils.FORMAT_SHOW_TIME)
    }
}