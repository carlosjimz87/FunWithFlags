package com.carlosjimz87.funwithflags.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.carlosjimz87.funwithflags.databinding.DetailsFragmentBinding
import timber.log.Timber

class DetailsFragment : Fragment() {
    private val detailsViewModel: DetailsViewModel by viewModels()
    private var binding: DetailsFragmentBinding? = null
    private val args: DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val fragmentBinding = DetailsFragmentBinding.inflate(inflater)

        binding = fragmentBinding
        Timber.i("DetailsFragment created")
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val code = args.codeCountry
        detailsViewModel.getCountryDetails(code)
        Toast.makeText(requireContext(), "Country passed $code", Toast.LENGTH_SHORT).show()
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = detailsViewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}