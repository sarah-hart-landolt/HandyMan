package com.example.handyman.data.entities

import androidx.room.Entity

@Entity(tableName = "address")

data class Address (
    val street:String,
    val city:String,
    val state:String,
    val postalCode:String,
    val country:String
)