package com.vickikbt.covid_19statapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ramotion.foldingcell.FoldingCell
import com.vickikbt.covid_19statapp.R
import com.vickikbt.covid_19statapp.data.db.entity.CountriesCoronaDataEntry


internal class RecyclerViewAdapter(
    private var countriesList: MutableList<CountriesCoronaDataEntry>
) : RecyclerView.Adapter<RecyclerViewAdapter.CountriesViewHolder>() {

    var countriesFilteredList: MutableList<CountriesCoronaDataEntry>? = null

    init {
        countriesFilteredList = countriesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)

        return CountriesViewHolder(
            view
        )
    }

    override fun getItemCount() = countriesFilteredList!!.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.initialize(countriesFilteredList!![position])
    }


    class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewCountryFolded: TextView? = null
        var textViewCountryCasesFolded: TextView? = null
        var textViewCountryDeathFolded: TextView? = null
        var textViewCountryRecoveredFolded: TextView? = null

        var textViewCountryUnfolded: TextView? = null
        var textViewCountryCasesUnfolded: TextView? = null
        var textViewCountryDeathUnfolded: TextView? = null
        var textViewCountryRecoveredUnfolded: TextView? = null
        var textViewActiveCasesUnfolded: TextView? = null
        var textViewCriticalCasesUnfolded: TextView? = null
        var textViewCasesTodayUnfolded: TextView? = null
        var textViewDeathTodayUnfolded: TextView? = null

        var cardViewFolded: CardView? = null

        init {
            this.textViewCountryFolded = itemView.findViewById(R.id.textView_country_folded)
            this.textViewCountryCasesFolded =
                itemView.findViewById(R.id.textView_country_cases_number_folded)
            this.textViewCountryDeathFolded =
                itemView.findViewById(R.id.textView_country_deaths_number_folded)
            this.textViewCountryRecoveredFolded =
                itemView.findViewById(R.id.textView_country_recovered_number_folded)

            this.textViewCountryUnfolded = itemView.findViewById(R.id.textView_country_unfolded)
            this.textViewCountryCasesUnfolded =
                itemView.findViewById(R.id.textView_country_cases_number_unfolded)
            this.textViewCountryDeathUnfolded =
                itemView.findViewById(R.id.textView_country_deaths_number_unfolded)
            this.textViewCountryRecoveredUnfolded =
                itemView.findViewById(R.id.textView_country_recovered_number_unfolded)
            this.textViewActiveCasesUnfolded =
                itemView.findViewById(R.id.textView_active_cases_details)
            this.textViewCriticalCasesUnfolded =
                itemView.findViewById(R.id.textView_critical_details)
            this.textViewCasesTodayUnfolded =
                itemView.findViewById(R.id.textView_today_cases_details)
            this.textViewDeathTodayUnfolded =
                itemView.findViewById(R.id.textView_today_death_details)

            this.cardViewFolded = itemView.findViewById(R.id.cardView_folded)

            val fc = itemView.findViewById<View>(R.id.folding_cell) as FoldingCell
            fc.tag = cardViewFolded

            fc.setOnClickListener {
                fc.toggle(false)
            }
        }

        fun initialize(data: CountriesCoronaDataEntry) {
            textViewCountryFolded?.text = data.country
            textViewCountryCasesFolded?.text = data.cases.toString()
            textViewCountryDeathFolded?.text = data.deaths.toString()
            textViewCountryRecoveredFolded?.text = data.recovered.toString()

            textViewCountryUnfolded?.text = data.country
            textViewCountryCasesUnfolded?.text = data.cases.toString()
            textViewCountryDeathUnfolded?.text = data.deaths.toString()
            textViewCountryRecoveredUnfolded?.text = data.recovered.toString()
            textViewActiveCasesUnfolded?.text = data.active.toString()
            textViewCriticalCasesUnfolded?.text = data.critical.toString()
            textViewCasesTodayUnfolded?.text = data.todayCases.toString()
            textViewDeathTodayUnfolded?.text = data.todayDeaths.toString()

        }
    }
}