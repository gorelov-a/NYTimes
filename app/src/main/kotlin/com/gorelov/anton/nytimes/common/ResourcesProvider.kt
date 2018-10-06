package com.gorelov.anton.nytimes.common

import android.content.Context
import android.support.annotation.StringRes
import javax.inject.Inject

class ResourcesProvider @Inject constructor(private val context: Context) {

    fun getString(@StringRes resId: Int, vararg args: Any): String = context.getString(resId, *args)

}