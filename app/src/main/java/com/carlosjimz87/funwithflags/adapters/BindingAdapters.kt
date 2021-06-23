package com.carlosjimz87.funwithflags.adapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.funwithflags.domain.Country
import com.carlosjimz87.funwithflags.fragments.list.CountriesApiStatus
import timber.log.Timber


private fun setStates(
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
        CountriesApiStatus.DONE -> {
            listView?.visibility = View.VISIBLE
            textView?.visibility = View.GONE
        }
    }
}

@BindingAdapter("countriesState")
fun bindViewState(
    stateView: TextView,
    status: CountriesApiStatus?,
) {
    setStates(textView = stateView, status = status)
}

@BindingAdapter("listState")
fun bindListState(
    listView: RecyclerView,
    status: CountriesApiStatus?,
) {
    setStates(listView = listView, status = status)
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
