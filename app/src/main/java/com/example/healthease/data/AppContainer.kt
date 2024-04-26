package com.example.healthease.data

import android.content.Context

interface AppContainer {
    val dataRepository: AppDataRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val dataRepository: AppDataRepository by lazy {
        OfflineAppDataRepository(AppDatabase.getDatabase(context).appDataDao())
    }
}