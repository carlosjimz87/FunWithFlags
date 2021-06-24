package com.carlosjimz87.funwithflags.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.funwithflags.databinding.ListItemBinding
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.utils.justify
import timber.log.Timber

interface SelectedCountryListener {
    fun onCountryClicked(country: Country)
}

class CountryListAdapter(private val selectedCountryListener: SelectedCountryListener) :
    ListAdapter<Country, CountryListAdapter.CountryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(ListItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    private class DiffCallback : DiffUtil.ItemCallback<Country>() {

        override fun areItemsTheSame(oldItem: Country, newItem: Country) =
            oldItem.code == newItem.code

        override fun areContentsTheSame(oldItem: Country, newItem: Country) =
            oldItem.flag == newItem.flag
    }

    override fun submitList(list: MutableList<Country>?, commitCallback: Runnable?) {
        super.submitList(list, commitCallback)
    }

    override fun submitList(list: MutableList<Country>?) {
        super.submitList(list)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = currentList[position]

        holder.bind(country, selectedCountryListener)
    }

    class CountryViewHolder(
        private var binding:
        ListItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country, listener: SelectedCountryListener) {
            with(binding) {
                this.country = country
                countryName.text = country.name.justify()
                countryLayout.setOnClickListener {
                    listener.onCountryClicked(country)
                    Timber.i("Country ${country.code} clicked")
                }
                executePendingBindings()
            }
        }
    }


}
