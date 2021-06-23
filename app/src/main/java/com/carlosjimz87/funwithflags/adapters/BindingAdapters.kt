package com.carlosjimz87.funwithflags.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.carlosjimz87.funwithflags.R
import com.carlosjimz87.funwithflags.domain.list.Country
import com.carlosjimz87.funwithflags.fragments.list.CountriesApiStatus
import timber.log.Timber

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    val imageLoader = ImageLoader.Builder(imgView.context)
        .componentRegistry {
            add(SvgDecoder(imgView.context))
        }
        .build()

    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri, imageLoader) {
            placeholder(R.drawable.loading_animator)
            error(R.drawable.broken_image)
        }
    }
}

@BindingAdapter("countriesState")
fun bindViewState(
    stateView: TextView,
    status: CountriesApiStatus?,
) {
    updateListFragmentUI(textView = stateView, status = status)
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
        adapter.submitList(data)
    }
}


fun updateListFragmentUI(
    status: CountriesApiStatus?,
    listView: RecyclerView? = null,
    textView: TextView? = null,
) {
    when (status) {
        CountriesApiStatus.LOADING -> {
            listView?.visibility = View.GONE
            textView?.visibility = View.VISIBLE
            textView?.text = "LOADING"
        }
        CountriesApiStatus.ERROR -> {
            listView?.visibility = View.GONE
            textView?.visibility = View.VISIBLE
            textView?.text = "ERROR"
        }
        CountriesApiStatus.SUCCESS -> {
            listView?.visibility = View.VISIBLE
            textView?.visibility = View.GONE
        }
    }
}

