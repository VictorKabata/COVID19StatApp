package com.vickikbt.covid_19statapp.adapter

import com.vickikbt.covid_19statapp.R
import com.vickikbt.covid_19statapp.db.entity.CountriesCoronaDataEntry
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.recyclerview_item.*

class GroupRecyclerView(val countryList: MutableList<CountriesCoronaDataEntry>) :
    com.xwray.groupie.kotlinandroidextensions.Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            val data=countryList[position]

            textView_country.text=data.country
        }
    }

    override fun getLayout() = R.layout.recyclerview_item


}