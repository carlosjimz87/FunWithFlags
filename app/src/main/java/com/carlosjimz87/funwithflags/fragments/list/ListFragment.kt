package com.carlosjimz87.funwithflags.fragments.list

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.funwithflags.R
import com.carlosjimz87.funwithflags.adapters.CountryListAdapter
import com.carlosjimz87.funwithflags.databinding.ListFragmentBinding
import com.carlosjimz87.funwithflags.fragments.BaseFragment
import com.carlosjimz87.funwithflags.utils.addDividerShape
import timber.log.Timber
import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller

class ListFragment : BaseFragment() {
    private var binding: ListFragmentBinding? = null

    private var countryListAdapter: CountryListAdapter? = null

    private val listViewModel: ListViewModel by viewModels()

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

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = listViewModel

            setupAdapter()
            setupRecyclerView(countriesRV, fastScroller)
        }
        observeErrorState()
    }

    init {
        setHasOptionsMenu(true)
    }

    private fun observeErrorState() {
        listViewModel.error.observe(viewLifecycleOwner, {
            showApiError()
        })
    }

    private fun setupAdapter() {
        countryListAdapter = CountryListAdapter {
            val action =
                ListFragmentDirections.actionListFragmentToDetailsFragment(it.code)
            findNavController().navigate(action)
        }
    }


    private fun setupRecyclerView(
        countriesRecyclerView: RecyclerView,
        fastScroller: VerticalRecyclerViewFastScroller,
    ) {
        countriesRecyclerView.adapter = countryListAdapter
        countriesRecyclerView.addDividerShape(requireContext(), R.drawable.divider_shape)
        fastScroller.setRecyclerView(countriesRecyclerView)
        countriesRecyclerView.setOnScrollListener(fastScroller.onScrollListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        countryListAdapter = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_view_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                countryListAdapter?.filter?.filter(newText)
                return false
            }
        })
    }
}