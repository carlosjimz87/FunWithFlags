package com.carlosjimz87.funwithflags.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.carlosjimz87.funwithflags.adapters.CountryListAdapter
import com.carlosjimz87.funwithflags.databinding.ListFragmentBinding
import timber.log.Timber

class ListFragment : Fragment() {
    private val listViewModel: ListViewModel by viewModels()
    private var binding: ListFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val fragmentBinding = ListFragmentBinding.inflate(inflater)

        binding = fragmentBinding
        Timber.i("ListFragment created")
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = listViewModel
            countriesRV.adapter = CountryListAdapter()
        }
        Timber.i("ListViewModel created")
    }
}