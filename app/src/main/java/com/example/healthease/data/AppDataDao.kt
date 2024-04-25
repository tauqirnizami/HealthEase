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

//    @Query("SELECT * FROM appData WHERE type = :type ORDER BY id DESC LIMIT 1")
//    suspend fun getLastDateData(type: String): AppData?
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertIfNotExists(appData: AppData)

}