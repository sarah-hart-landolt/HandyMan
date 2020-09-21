package com.example.handyman.data.entities

import androidx.room.Entity

@Entity(tableName = "coordinate")

data class Coordinate (
    val latitude:Double,
    val longitude:Double
)