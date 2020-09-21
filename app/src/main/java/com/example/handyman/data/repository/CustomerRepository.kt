package com.example.handyman.data.repository

import com.example.handyman.data.local.CustomerDao
import com.example.handyman.data.remote.CustomerRemoteDataSource
import com.example.handyman.utils.performGetOperation
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val remoteDataSource: CustomerRemoteDataSource,
    private val localDataSource: CustomerDao
) {


    fun getCustomers() = performGetOperation(
        databaseQuery = { localDataSource.getAllCustomers() },
        networkCall = { remoteDataSource.getCustomers() },
        saveCallResult = { localDataSource.insertAll(it) }
    )

}