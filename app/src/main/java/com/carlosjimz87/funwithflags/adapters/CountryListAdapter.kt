package com.carlosjimz87.funwithflags.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.funwithflags.databinding.ListItemBinding
import com.carlosjimz87.funwithflags.domain.list.Country
import com.carlosjimz87.funwithflags.utils.justify

class CountryListAdapter : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    private lateinit var countries: List<Country>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(ListItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    fun submitList(countriesList: List<Country>?) {
        countriesList?.let {
            countries = it
        }
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country, holder)
    }

    class CountryViewHolder(
        private var binding:
        ListItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country, holder: CountryViewHolder) {
            with(binding) {
                this.country = country
                countryName.text = country.name.justify()
                countryLayout.setOnClickListener{
                    val context =holder.itemView.context
                    navigateToFragment(context, country.code)
                }
                executePendingBindings()
            }
        }

        private fun navigateToFragment(context: Context, code:String){
            Toast.makeText( context,"Country $code clicked", Toast.LENGTH_LONG).show()
        }
    }


}
