package com.carlosjimz87.funwithflags.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.funwithflags.R
import com.carlosjimz87.funwithflags.fragments.CountriesApiStatus
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.utils.getCompatDrawable
import com.carlosjimz87.funwithflags.utils.loadImage
import timber.log.Timber
import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    loadImage(imgView, imgUrl, imgDrawable = null, placeholders = true)
}

@BindingAdapter("imageDrawable")
fun bindDrawable(imgView: ImageView, drawable: Drawable?) {
    loadImage(imgView, imgDrawable = drawable, placeholders = false)
}

@BindingAdapter("scrollerState")
fun bindScrollerState(
    view: VerticalRecyclerViewFastScroller,
    status: CountriesApiStatus?,
) {
    showLoadingState(activeView = view, status = status)
}

@BindingAdapter("activeViewState")
fun bindActiveViewState(
    view: View,
    status: CountriesApiStatus?,
) {
    showLoadingState(activeView = view, status = status)
}

@BindingAdapter("inactiveViewState")
fun bindInactiveViewState(
    view: ImageView,
    status: CountriesApiStatus?,
) {
    showLoadingState(inactiveView = view, status = status)
}

@BindingAdapter("listState")
fun bindListState(
    listView: RecyclerView,
    status: CountriesApiStatus?,
) {
    showLoadingState(activeView = listView, status = status)
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Country>?,
) {
    data?.let {
        Timber.i("Submitting new list (${data.size} countries)")
        val adapter = recyclerView.adapter as CountryListAdapter
        adapter.submitAllLists(data.toMutableList(),data.toMutableList())
    }
}

fun showLoadingState(
    status: CountriesApiStatus?,
    activeView: View? = null,
    inactiveView: View? = null,
) {
    when (status) {
        CountriesApiStatus.SUCCESS -> {
            activeView?.visibility = View.VISIBLE
            inactiveView?.visibility = View.GONE
        }
        CountriesApiStatus.LOADING -> {
            activeView?.visibility = View.GONE
            inactiveView?.let {
                it.visibility = View.VISIBLE
                loadImage(it as ImageView,
                    imgDrawable = it.context.getCompatDrawable(R.drawable.loading_animator),
                    placeholders = false)
            }
        }
        else -> {
            activeView?.visibility = View.GONE
            inactiveView?.let {
                it.visibility = View.VISIBLE
                loadImage(it as ImageView,
                    imgDrawable = it.context.getCompatDrawable(R.drawable.broken_image),
                    placeholders = false)
            }
        }
    }
}