package com.carlosjimz87.funwithflags.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.carlosjimz87.funwithflags.R
import com.carlosjimz87.funwithflags.adapters.CountryListAdapter
import com.carlosjimz87.funwithflags.databinding.DetailsFragmentBinding
import com.carlosjimz87.funwithflags.databinding.ListFragmentBinding
import com.carlosjimz87.funwithflags.utils.addDividerShape
import timber.log.Timber

class DetailsFragment : Fragment() {
    private val detailsViewModel: DetailsViewModel by viewModels()
    private var binding: DetailsFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val fragmentBinding = DetailsFragmentBinding.inflate(inflater)

        binding = fragmentBinding
        Timber.i("DetailsFragment created")
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
//            viewModel = detailsViewModel

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}