package com.carlosjimz87.funwithflags.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addDividerShape(context: Context, resource: Int): RecyclerView {
    val dividerItemDecoration = DividerItemDecoration(context,
        DividerItemDecoration.VERTICAL)

    val dividerShape: Drawable = ResourcesCompat.getDrawable(context.resources,
        resource,
        null)!!

    dividerItemDecoration.setDrawable(dividerShape)
    this.addItemDecoration(dividerItemDecoration)
    return this
}