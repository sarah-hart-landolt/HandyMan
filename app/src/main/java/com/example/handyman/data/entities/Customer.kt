package com.example.handyman.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(tableName = "customers")
data class Customer (
    @PrimaryKey
    val identifier: Int,
    val visitOrder: Int,
    val name:String,
    val phoneNumber:String,
    @Embedded
    val profilePicture:ProfilePicture,
    @Embedded
    val location:Location,
    val serviceReason:String
)
