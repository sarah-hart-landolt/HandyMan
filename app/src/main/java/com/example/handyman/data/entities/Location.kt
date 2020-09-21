package com.example.handyman.data.entities

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "location")

data class Location (
    @Embedded

    val address:Address,
    @Embedded

    val coordinate:Coordinate
)