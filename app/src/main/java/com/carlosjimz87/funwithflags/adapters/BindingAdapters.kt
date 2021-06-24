package com.carlosjimz87.funwithflags.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.funwithflags.R
import com.carlosjimz87.funwithflags.fragments.list.CountriesApiStatus
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.utils.getCompatDrawable
import com.carlosjimz87.funwithflags.utils.loadImage
import timber.log.Timber

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    loadImage(imgView, imgUrl)
}

@BindingAdapter("countriesState")
fun bindViewState(
    stateView: ImageView,
    status: CountriesApiStatus?,
) {
    updateListFragmentUI(imageView = stateView, status = status)
}

@BindingAdapter("listState")
fun bindListState(
    listView: RecyclerView,
    status: CountriesApiStatus?,
) {
    updateListFragmentUI(listView = listView, status = status)
}


@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Country>?,
) {
    data?.let {
        Timber.i("Submitting new list (${data.size} countries)")
        val adapter = recyclerView.adapter as CountryListAdapter
        adapter.submitList(data.toMutableList())
    }
}


fun updateListFragmentUI(
    status: CountriesApiStatus?,
    listView: RecyclerView? = null,
    imageView: ImageView? = null,
) {
    when (status) {
        CountriesApiStatus.SUCCESS -> {
            listView?.visibility = View.VISIBLE
            imageView?.visibility = View.GONE
        }
        CountriesApiStatus.LOADING -> {
            listView?.visibility = View.GONE
            imageView?.let {
                it.visibility = View.VISIBLE
                loadImage(it,
                    imgDrawable = it.context.getCompatDrawable(R.drawable.loading_image))
            }
        }
        else -> {
            listView?.visibility = View.GONE
            imageView?.let {
                it.visibility = View.VISIBLE
                loadImage(it,
                    imgDrawable = it.context.getCompatDrawable(R.drawable.broken_image))
            }
        }
    }
}