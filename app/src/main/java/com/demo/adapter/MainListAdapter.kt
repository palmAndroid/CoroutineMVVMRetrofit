package com.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.entity.Country
import kotlinx.android.synthetic.main.item_country.view.*

class MainListAdapter (var countries: ArrayList<Country>): RecyclerView.Adapter<MainListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class CountryViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val countryName = view.textView2

        fun bind(country: Country) {
            countryName.text = country.countryName
        }
    }
}