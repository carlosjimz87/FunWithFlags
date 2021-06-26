package com.carlosjimz87.funwithflags.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.carlosjimz87.funwithflags.R
import com.carlosjimz87.funwithflags.databinding.DetailsFragmentBinding
import com.carlosjimz87.funwithflags.fragments.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber

class DetailsFragment : BaseFragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private val detailsViewModel: DetailsViewModel by viewModels()
    private var binding: DetailsFragmentBinding? = null
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val fragmentBinding = DetailsFragmentBinding.inflate(inflater)

        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val code = args.codeCountry
        Timber.i("Country ($code) sent to Details")
        setupViewModel(code)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = detailsViewModel
        }
        observeLatLng()
        observeErrorState()
        setupGoogleMap()
    }

    private fun setupViewModel(code: String) {
        detailsViewModel.setContext(requireContext())
        detailsViewModel.getCountryDetails(code)
    }

    private fun observeErrorState() {
        detailsViewModel.error.observe(viewLifecycleOwner, {
            showApiError()
        })
    }

    private fun observeLatLng() {
        detailsViewModel.position.observe(viewLifecycleOwner, {
            val position = LatLng(it.second[0], it.second[1])
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(position).title(it.first))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position))
        })
    }

    private fun setupGoogleMap() {
        val mapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) = run { mMap = googleMap }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}