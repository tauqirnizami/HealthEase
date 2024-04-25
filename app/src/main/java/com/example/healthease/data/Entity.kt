package com.example.healthease.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appData")
data class AppData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: String,
    val content: String,
)