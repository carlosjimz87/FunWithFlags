package com.carlosjimz87.funwithflags.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.funwithflags.fragments.CountriesApiStatus
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

@BindingAdapter("activeViewState")
fun activeViewState(
    view: View,
    status: CountriesApiStatus?,
) {
    showLoadingState(activeView = view, status = status)
}


@BindingAdapter("inactiveViewState")
fun inactiveViewState(
    view: View,
    status: CountriesApiStatus?,
) {
    showLoadingState(inactiveView = view, status = status)
}

fun showLoadingState(
    status: CountriesApiStatus?,
    activeView: View? = null,
    inactiveView: View? = null,
    activeLayout: ViewGroup? = null,
    inactiveLayout: ViewGroup? = null,
) {
    when (status) {
        CountriesApiStatus.SUCCESS -> {
            activeView?.visibility = View.VISIBLE
            activeLayout?.visibility = View.VISIBLE
            inactiveView?.visibility = View.GONE
            inactiveLayout?.visibility = View.GONE
        }
        else -> {
            activeLayout?.visibility = View.GONE
            activeView?.visibility = View.GONE
            inactiveLayout?.visibility = View.VISIBLE
            inactiveView?.visibility = View.VISIBLE
        }
    }
}