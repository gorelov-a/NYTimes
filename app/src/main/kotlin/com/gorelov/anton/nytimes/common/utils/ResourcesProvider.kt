package com.gorelov.anton.nytimes.common.utils

import android.content.Context
import android.support.annotation.ArrayRes
import android.support.annotation.StringRes
import javax.inject.Inject

class ResourcesProvider @Inject constructor(private val context: Context) {

    fun getString(@StringRes resId: Int, vararg args: Any): String = context.getString(resId, *args)

    fun getStringArray(@ArrayRes resId: Int): Array<String> = context.resources.getStringArray(resId)

}