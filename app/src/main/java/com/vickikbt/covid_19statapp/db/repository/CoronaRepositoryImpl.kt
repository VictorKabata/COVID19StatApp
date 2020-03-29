package com.vickikbt.covid_19statapp.db.repository

import androidx.lifecycle.LiveData
import com.vickikbt.covid_19statapp.db.GlobalCoronaStatDAO
import com.vickikbt.covid_19statapp.db.entity.GlobalCoronaData
import com.vickikbt.covid_19statapp.network.GlobalStatNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class CoronaRepositoryImpl(
    private val globalCoronaStatDAO: GlobalCoronaStatDAO,
    private val globalCoronaDataSource: GlobalStatNetworkDataSource
) : CoronaRepository {

    init {
        globalCoronaDataSource.downloadedGlobalStats.observeForever { newGlobalStat ->
            persistFetchedCurrentGlobalStat(newGlobalStat)
        }
    }

    override suspend fun getGlobalStat(): LiveData<GlobalCoronaData> {
        return withContext(Dispatchers.IO) {
            return@withContext globalCoronaStatDAO.getGlobalCoronaStat()
        }
    }

    private fun persistFetchedCurrentGlobalStat(fetchedStat: GlobalCoronaData) {
        GlobalScope.launch(Dispatchers.IO) {
            globalCoronaStatDAO.upsert(fetchedStat)
        }
    }

    private suspend fun initGlobalStats() {
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentStatistics()
    }

    private suspend fun fetchCurrentStatistics() {
        globalCoronaDataSource.fetchCurrentGlobalStat()
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val tenMinsAgo = ZonedDateTime.now().minusMinutes(10)
        return (lastFetchTime.isBefore(tenMinsAgo))
    }
}