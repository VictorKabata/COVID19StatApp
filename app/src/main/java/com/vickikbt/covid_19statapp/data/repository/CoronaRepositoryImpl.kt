package com.vickikbt.covid_19statapp.data.repository

import androidx.lifecycle.LiveData
import com.vickikbt.covid_19statapp.data.db.dao.CountriesCoronaStatDAO
import com.vickikbt.covid_19statapp.data.db.dao.GlobalCoronaStatDAO
import com.vickikbt.covid_19statapp.data.db.entity.CountriesCoronaDataEntry
import com.vickikbt.covid_19statapp.data.db.entity.GlobalCoronaData
import com.vickikbt.covid_19statapp.data.model.CountriesCoronaDataResponse
import com.vickikbt.covid_19statapp.data.network.CoronaStatNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class CoronaRepositoryImpl(
    private val globalCoronaStatDAO: GlobalCoronaStatDAO,
    private val countriesCoronaStatDAO: CountriesCoronaStatDAO,
    private val coronaDataSource: CoronaStatNetworkDataSource
) : CoronaRepository {

    init {
        coronaDataSource.apply {
            downloadedGlobalStats.observeForever { newGlobalStat ->
                persistFetchedCurrentGlobalStat(newGlobalStat)
            }

            downloadCountriesStat.observeForever { newCountriesStat ->
                persistFetchedCurrentCountriesStat(newCountriesStat)
            }
        }
    }

    override suspend fun getGlobalStat(): LiveData<GlobalCoronaData> {
        return withContext(Dispatchers.IO) {
            initStats()
            return@withContext globalCoronaStatDAO.getGlobalCoronaStat()
        }
    }

    override suspend fun getCountriesStat(): LiveData<List<CountriesCoronaDataEntry>> {
        return withContext(Dispatchers.IO) {
            initStats()
            return@withContext countriesCoronaStatDAO.getCountriesStat()
        }
    }

    private fun persistFetchedCurrentGlobalStat(fetchedGlobalStat: GlobalCoronaData) {
        GlobalScope.launch(Dispatchers.IO) {
            globalCoronaStatDAO.upsert(fetchedGlobalStat)
        }
    }

    private fun persistFetchedCurrentCountriesStat(fetchedStat: CountriesCoronaDataResponse) {

        GlobalScope.launch(Dispatchers.IO) {
            countriesCoronaStatDAO.upsert(fetchedStat)
//            Log.e("Debug", "Saved countries stat from retrofit to room db.")
        }

    }

    private suspend fun initStats() {
        if (isGlobalStatFetchNeeded(ZonedDateTime.now().minusMinutes(2)))
            fetchGlobalStatistics()
        if (isCountriesStatFetchNeeded(ZonedDateTime.now().minusMinutes(2)))
            fetchCountriesStatistics()
    }

    private suspend fun fetchGlobalStatistics() {
        coronaDataSource.fetchGlobalStat()
    }

    private suspend fun fetchCountriesStatistics() {
        coronaDataSource.fetchCountriesStat()
    }

    private fun isGlobalStatFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val twoMinsAgo = ZonedDateTime.now().minusMinutes(2)
        return (lastFetchTime.isBefore(twoMinsAgo))
    }

    private fun isCountriesStatFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val twoMinsAgo = ZonedDateTime.now().minusMinutes(2)
        return (lastFetchTime.isBefore(twoMinsAgo))
    }
}