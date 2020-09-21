package com.example.handyman.data.remote

import javax.inject.Inject

class CustomerRemoteDataSource @Inject constructor(
    private val customerService: CustomerService
): BaseDataSource() {
    suspend fun getCustomers() = getResult { customerService.getAllCustomers() }
}