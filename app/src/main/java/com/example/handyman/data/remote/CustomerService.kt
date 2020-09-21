package com.example.handyman.data.remote


import com.example.handyman.data.entities.Customer
import retrofit2.Response
import retrofit2.http.GET

interface CustomerService {
    @GET("celerocustomers.json")
    suspend fun getAllCustomers() : Response<List<Customer>>

}