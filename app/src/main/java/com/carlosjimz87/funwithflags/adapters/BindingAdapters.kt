package com.carlosjimz87.funwithflags.adapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.utils.loadImage
import timber.log.Timber

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    loadImage(imgView, imgUrl, imgDrawable = null, placeholders = true)
}

@BindingAdapter("imageDrawable")
fun bindDrawable(imgView: ImageView, drawable: Drawable?) {
    loadImage(imgView, imgDrawable = drawable, placeholders = false)
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Country>?,
) {
    data?.let {
        Timber.i("Submitting new list (${data.size} countries)")
        val adapter = recyclerView.adapter as CountryListAdapter
        adapter.submitAllLists(data.toMutableList(), data.toMutableList())
    }
}