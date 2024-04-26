package com.example.healthease.data

import kotlinx.coroutines.flow.Flow

interface AppDataRepository {
    fun getDataStream(type: String): Flow<List<AppData?>>

    suspend fun upsertData(data: AppData)

    suspend fun deleteData(data: AppData)
}