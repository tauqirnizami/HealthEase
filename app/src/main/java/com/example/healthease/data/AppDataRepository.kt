package com.example.healthease.data

import kotlinx.coroutines.flow.Flow

interface AppDataRepository {
    fun getDataStream(type: String): Flow<List<AppData?>>

    /**
     * Insert item in the data source
     */
    suspend fun upsertData(data: AppData)

    /**
     * Delete item from the data source
     */
    suspend fun deleteData(data: AppData)

//    suspend fun getLastDateData(type: String): AppData? = appDataDao.getLastDateData(type)
//
//    suspend fun updateContent(id: Int, newContent: String) {
//        val dataToUpdate = appDataDao.getDataById(id)
//        if (dataToUpdate != null) {
//            dataToUpdate.content = newContent
//            appDataDao.upsert(dataToUpdate)
//        }
//    }

//    suspend fun getLastDateData(type: String): AppData?
//
//    suspend fun updateContent(id: Int, newContent: String)

}