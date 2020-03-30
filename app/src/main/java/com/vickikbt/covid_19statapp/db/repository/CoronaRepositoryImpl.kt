package com.vickikbt.covid_19statapp.db.repository

import androidx.lifecycle.LiveData
import com.vickikbt.covid_19statapp.db.CountriesCoronaStatDAO
import com.vickikbt.covid_19statapp.db.GlobalCoronaStatDAO
import com.vickikbt.covid_19statapp.db.entity.CountriesCoronaDataEntry
import com.vickikbt.covid_19statapp.db.entity.GlobalCoronaData
import com.vickikbt.covid_19statapp.network.CoronaStatNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
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
            initGlobalStats()
            return@withContext globalCoronaStatDAO.getGlobalCoronaStat()
        }
    }

    override suspend fun getCountriesStat(): LiveData<List<CountriesCoronaDataEntry>> {
        return withContext(Dispatchers.IO) {
            initGlobalStats()
            return@withContext countriesCoronaStatDAO.getAllCountries()
        }
    }

    private fun persistFetchedCurrentGlobalStat(fetchedStat: GlobalCoronaData) {
        GlobalScope.launch(Dispatchers.IO) {
            globalCoronaStatDAO.upsert(fetchedStat)
        }
    }

    private fun persistFetchedCurrentCountriesStat(fetchedStat: List<CountriesCoronaDataEntry>) {

        fun deleteOldEntries() {
            val today = LocalDate.now()
            //countriesCoronaStatDAO.deleteOldEntries(today)
        }

        GlobalScope.launch(Dispatchers.IO) {
            deleteOldEntries()
            countriesCoronaStatDAO.upsert(fetchedStat)
        }

    }

    private suspend fun initGlobalStats() {
        if (isGlobalStatFetchNeeded(ZonedDateTime.now().minusMinutes(5)))
            fetchGlobalStatistics()
        if (isCountriesStatFetchNeeded(ZonedDateTime.now().minusMinutes(5)))
            fetchCountriesStatistics()
    }

    private suspend fun fetchGlobalStatistics() {
        coronaDataSource.fetchGlobalStat()
    }

    private suspend fun fetchCountriesStatistics() {
        coronaDataSource.fetchCountriesStat()
    }

    private fun isGlobalStatFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val tenMinsAgo = ZonedDateTime.now().minusMinutes(10)
        return (lastFetchTime.isBefore(tenMinsAgo))
    }

    private fun isCountriesStatFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val tenMinsAgo = ZonedDateTime.now().minusMinutes(10)
        return (lastFetchTime.isBefore(tenMinsAgo))
    }
}