package com.example.healthease.ui.theme

import android.app.Application
import com.example.healthease.data.AppContainer
import com.example.healthease.data.AppDataContainer

class HealthEaseApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}