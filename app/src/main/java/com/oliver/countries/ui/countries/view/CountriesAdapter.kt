package com.oliver.countries.ui.countries.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oliver.countries.data.model.Country
import com.oliver.countries.databinding.CountryListItemBinding

class CountriesAdapter(var countryList: List<Country>) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            CountryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { it(countryList[position]) }
            }
        }
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    inner class CountryViewHolder(private val itemBinding: CountryListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(position: Int) {
            itemView.apply {
                val country = countryList[position]
                itemBinding.tvNameRegion.text = country.name.plus(", ").plus(country.region)
                itemBinding.tvCode.text = country.code
                itemBinding.tvCapital.text = country.capital
            }
        }
    }

    private var onItemClickListener: ((Country) -> Unit)? = null

    fun setOnItemClickListener(listener: (Country) -> Unit) {
        onItemClickListener = listener
    }
}
