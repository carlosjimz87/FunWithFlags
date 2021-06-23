package com.carlosjimz87.funwithflags.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.funwithflags.databinding.ListItemBinding
import com.carlosjimz87.funwithflags.databinding.ListItemBindingImpl
import com.carlosjimz87.funwithflags.domain.list.Country
import timber.log.Timber

class CountryListAdapter : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    private lateinit var countries: List<Country>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(ListItemBindingImpl.inflate(
            LayoutInflater.from(parent.context)))
    }

    fun submitList(countriesList: List<Country>?) {
        countriesList?.let {
            countries = it
            Timber.i("Setting country list (:${countries.size} countries)")
        }
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        Timber.i("Binding country:${country.code}")
        holder.bind(country)
    }

    class CountryViewHolder(
        private var binding:
        ListItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            with(binding) {
                this.country = country
                countryName.text = country.name
                executePendingBindings()
            }
        }
    }
}
