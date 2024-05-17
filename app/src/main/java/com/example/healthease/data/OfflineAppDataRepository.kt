package com.example.healthease.data

import kotlinx.coroutines.flow.Flow

class OfflineAppDataRepository(private val appDataDao: AppDataDao) : AppDataRepository {
    override fun getDataStream(type: String): Flow<List<AppData>> = appDataDao.getData(type)
    override suspend fun deleteData(data: AppData) = appDataDao.delete(data)
    override suspend fun upsertData(data: AppData) = appDataDao.upsert(data)
}