package com.example.healthease.ui.theme

data class CalculationsUiState(
    val bmi: Float = 0.0f,
    val currentWeight: Float = 0.0f,
    val goalWeight: Float = 0.0f,
    val currentCaloryNeed: Float = 0.0f,
    val goalCaloryNeed: Float = 0.0f,
    val currentFluidNeed: Float = 0.0f,
    val goalFluidNeed: Float = 0.0f,

    /***
     * Exercise Section
     */
    var workoutSearchScreen: Boolean = true,
    var dietSearchScreen: Boolean = true,

    var level: Int = 1,

    var currentIndex: Int = 0
)