package com.example.healthease.ui.theme

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            CalculationsViewModel(

//                this.createSavedStateHandle(),
                healthEaseApplication().container.dataRepository
            )
        }
    }
}

fun CreationExtras.healthEaseApplication(): HealthEaseApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as HealthEaseApplication)