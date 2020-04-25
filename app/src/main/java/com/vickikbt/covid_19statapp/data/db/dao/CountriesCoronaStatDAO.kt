package com.vickikbt.covid_19statapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.covid_19statapp.data.model.CountriesCoronaDataResponse
import com.vickikbt.covid_19statapp.data.db.entity.CountriesCoronaDataEntry

@Dao
interface CountriesCoronaStatDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(countriesCoronaDataResponse: CountriesCoronaDataResponse)

    @Query("select * from country_statistics order by cases desc")
    fun getCountriesStat(): LiveData<List<CountriesCoronaDataEntry>>


//    @Query("delete from country_statistics where something")

}