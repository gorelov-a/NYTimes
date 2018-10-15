package com.gorelov.anton.nytimes.common

import android.content.Context
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View


class SpacesItemDecoration(context: Context, @DimenRes itemOffsetId: Int) : RecyclerView.ItemDecoration() {
    private val space: Int = context.resources.getDimensionPixelSize(itemOffsetId)

    override fun getItemOffsets(rect: Rect, view: View,
                                parent: RecyclerView,
                                state: RecyclerView.State) {
        rect.left = space
        rect.right = space
        rect.bottom = space
        rect.top = space
    }
}