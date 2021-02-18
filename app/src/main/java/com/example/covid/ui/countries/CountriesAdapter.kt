package com.example.covid.ui.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid.R
import com.example.covid.model.CountryInfo
import kotlinx.android.synthetic.main.country_item.view.*

class CountriesAdapter(val countries: List<CountryInfo>) : RecyclerView.Adapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

}

class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind(country: CountryInfo){
        itemView.countryNameTV.text = country.name
        itemView.casesTV.text = country.cases.toString()
        //itemView.countryFlagIV() = country.countryInfo.flag
    }
}
