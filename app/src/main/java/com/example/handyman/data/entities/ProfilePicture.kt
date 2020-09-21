package com.example.handyman.data.entities

import androidx.annotation.Nullable
import androidx.room.Entity

@Entity(tableName = "profilePicture")

data class ProfilePicture (
    val large:String,
    val medium:String,
    val thumbnail:String
)