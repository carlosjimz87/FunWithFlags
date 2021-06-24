package com.carlosjimz87.funwithflags.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.carlosjimz87.funwithflags.R
import com.carlosjimz87.funwithflags.adapters.CountryListAdapter
import com.carlosjimz87.funwithflags.adapters.SelectedCountryListener
import com.carlosjimz87.funwithflags.databinding.ListFragmentBinding
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.utils.addDividerShape
import timber.log.Timber

class ListFragment : Fragment() {
    private val listViewModel: ListViewModel by viewModels()
    private var binding: ListFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val fragmentBinding = ListFragmentBinding.inflate(inflater)

        binding = fragmentBinding
        Timber.i("ListFragment created")
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(com.carlosjimz87.funwithflags.R.string.app_name)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = listViewModel

            countriesRV.adapter = CountryListAdapter(object : SelectedCountryListener {
                override fun onCountryClicked(country: Country) {
                    val action =
                        ListFragmentDirections.actionListFragmentToDetailsFragment(country.code)
                    findNavController().navigate(action)
                }
            })

            countriesRV.addDividerShape(requireContext(), R.drawable.divider_shape)
            fastScroller.setRecyclerView(countriesRV)
            countriesRV.setOnScrollListener(fastScroller.onScrollListener);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}