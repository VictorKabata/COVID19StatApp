package com.vickikbt.covid_19statapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.covid_19statapp.db.entity.GLOBAL_DATA_ID
import com.vickikbt.covid_19statapp.db.entity.GlobalCoronaData

@Dao()
interface GlobalCoronaStatDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(globalCoronaStat:GlobalCoronaData)

    @Query("select * from global_data where id=$GLOBAL_DATA_ID")
    fun getGlobalCoronaStat():LiveData<GlobalCoronaData>
}