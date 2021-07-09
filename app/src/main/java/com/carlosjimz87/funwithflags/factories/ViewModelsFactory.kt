package com.carlosjimz87.funwithflags.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carlosjimz87.funwithflags.App
import com.carlosjimz87.funwithflags.fragments.details.DetailsViewModel
import com.carlosjimz87.funwithflags.fragments.list.ListViewModel


class ViewModelsFactory(private val app: App) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java))
            return ListViewModel(app.countriesRepository) as T
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java))
            return DetailsViewModel(app, app.countriesRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}