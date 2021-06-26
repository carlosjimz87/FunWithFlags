package com.carlosjimz87.funwithflags.fragments

import androidx.fragment.app.Fragment
import com.carlosjimz87.funwithflags.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment() : Fragment() {

    fun showApiError(){
        Snackbar.make(requireView(), getString(R.string.api_error_message), Snackbar.LENGTH_SHORT)
            .show()
    }
}