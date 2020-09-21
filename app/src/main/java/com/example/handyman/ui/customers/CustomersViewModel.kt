package com.example.handyman.ui.customers

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.handyman.data.repository.CustomerRepository

class CustomersViewModel @ViewModelInject constructor(
    private val repository: CustomerRepository
) : ViewModel() {

    val customers = repository.getCustomers()
}
