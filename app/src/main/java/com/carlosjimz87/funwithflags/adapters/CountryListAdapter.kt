package com.carlosjimz87.funwithflags.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.funwithflags.databinding.ListItemBinding
import com.carlosjimz87.funwithflags.network.models.Country
import com.carlosjimz87.funwithflags.utils.filterListCountry
import com.carlosjimz87.funwithflags.utils.justify
import timber.log.Timber

interface SelectedCountryListener {
    fun onCountryClicked(country: Country)
}

class CountryListAdapter(private val selectedCountryListener: SelectedCountryListener) :
    ListAdapter<Country, CountryListAdapter.CountryViewHolder>(DiffCallback()), Filterable {
    private var countryFilter: CountryFilter? = CountryFilter()
    private var fullList: MutableList<Country>? = null
    private var filterEnabled = false

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

    fun submitAllLists(currentList: MutableList<Country>?, fullList: MutableList<Country>? = null) {
        submitList(currentList)
        this.fullList = fullList
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = currentList[position]
        holder.bind(holder, country, selectedCountryListener)
    }

    class CountryViewHolder(
        private var binding:
        ListItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(holder: CountryViewHolder, country: Country, listener: SelectedCountryListener) {
            with(binding) {
                this.country = country
                val name = NameAdapter.getCountryName(country, holder.itemView.context).justify()
                countryName.text = name
                countryLayout.setOnClickListener {
                    listener.onCountryClicked(country)
                    Timber.i("Country ${country.code} clicked")
                }
                executePendingBindings()
            }
        }
    }

    override fun getFilter(): Filter = countryFilter as Filter

    inner class CountryFilter(
    ) : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            filterEnabled = false
            val fList: MutableList<Country>
            val results = FilterResults()
            submitList(fullList)
            results.values = currentList

            if (constraint != null && constraint.length >= 2) {
                filterEnabled = true

                fList = filterListCountry(constraint, currentList)

                results.values = fList
            }
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (filterEnabled) {
                val toSubmit = results!!.values as MutableList<Country>
                submitList(toSubmit)
                notifyDataSetChanged()
            }
        }
    }
}

//private fun MutableList<Country>.addMutables(elements: MutableList<*>) {
//    for (elem in elements) {
//        this.add(elem as Country)
//    }
//}
