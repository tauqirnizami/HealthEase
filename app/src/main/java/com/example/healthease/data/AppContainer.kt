package com.example.healthease.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val dataRepository: AppDataRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineAppDataRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [AppDataRepository]
     */
    override val dataRepository: AppDataRepository by lazy {
        OfflineAppDataRepository(AppDatabase.getDatabase(context).appDataDao())
    }
}