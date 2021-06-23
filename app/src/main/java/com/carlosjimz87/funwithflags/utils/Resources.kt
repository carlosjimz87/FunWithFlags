package com.carlosjimz87.funwithflags.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat

fun getCompatDrawable(context: Context, id: Int, theme: Resources.Theme? = null): Drawable? {
    return ResourcesCompat.getDrawable(context.resources, id, theme)
}