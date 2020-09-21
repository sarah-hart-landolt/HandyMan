package com.example.handyman.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.handyman.data.entities.Customer

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customers ORDER BY visitOrder ASC")
    fun getAllCustomers() : LiveData<List<Customer>>

    @Query("SELECT * FROM customers WHERE identifier = :identifier")
    fun getCustomer(identifier: Int): LiveData<Customer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(customer: List<Customer>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: Customer)


}