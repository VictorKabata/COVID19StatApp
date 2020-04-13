package com.vickikbt.covid_19statapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val GLOBAL_DATA_ID=0

@Entity(tableName = "global_data")
data class GlobalCoronaData(
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
    val updated: Long
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = GLOBAL_DATA_ID
}