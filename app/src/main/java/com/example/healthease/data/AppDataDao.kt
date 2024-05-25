package com.example.healthease.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDataDao {
    @Upsert
    suspend fun upsert(appData: AppData)

    @Delete
    suspend fun delete(appData: AppData)

    @Query("SELECT * from appData WHERE type = :type ORDER BY id DESC")
    fun getData(type: String): Flow<List<AppData>>

    @Query("SELECT * from appData WHERE type = :type ORDER BY id DESC")
    suspend fun getDataList(type: String): List<AppData>
}