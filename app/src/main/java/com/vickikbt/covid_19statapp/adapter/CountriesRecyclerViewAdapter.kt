package com.vickikbt.covid_19statapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vickikbt.covid_19statapp.R
import com.vickikbt.covid_19statapp.db.entity.CountriesCoronaDataEntry
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class RecyclerViewAdapter(private var countriesList: MutableList<CountriesCoronaDataEntry>) :
    RecyclerView.Adapter<CountriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)

        return CountriesViewHolder(view)
    }

    override fun getItemCount() = countriesList.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val data = countriesList[position]

        val textViewCountry = holder.itemView.textView_country
        val textViewCountryCases = holder.itemView.textView_country_cases_number
        val textViewCountryDeaths = holder.itemView.textView_country_deaths_number
        val textViewCountryRecovered = holder.itemView.textView_country_recovered_number

        textViewCountry.text = data.country
        textViewCountryCases.text = data.cases.toString()
        textViewCountryDeaths.text = data.deaths.toString()
        textViewCountryRecovered.text = data.recovered.toString()

        holder.initialize(countriesList[position])
    }

}

class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun initialize(item: CountriesCoronaDataEntry) {

    }

}