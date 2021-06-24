package com.carlosjimz87.funwithflags.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.net.toUri
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.carlosjimz87.funwithflags.R


fun loadImage(
    imgView: ImageView,
    imgUrl: String? = null,
    imgDrawable: Drawable? = null,
    placeholders: Boolean,
) {

    with(getLoader(imgView.context)) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri, this) {
                if (placeholders) {
                    placeholder(R.drawable.loading_animator)
                    error(R.drawable.broken_image)
                }
            }
            return@let
        }

        imgDrawable?.let {
            imgView.load(imgDrawable, this) {
                if (placeholders) {
                    placeholder(R.drawable.loading_animator)
                    error(R.drawable.broken_image)
                }
            }
            return@let
        }
    }

}

fun getLoader(context: Context): ImageLoader {
    return ImageLoader.Builder(context)
        .componentRegistry {
            add(SvgDecoder(context))
        }
        .build()
}