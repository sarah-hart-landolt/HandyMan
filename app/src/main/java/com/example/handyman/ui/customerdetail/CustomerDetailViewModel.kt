package com.example.handyman.ui.characterdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.handyman.data.entities.Customer
import com.example.handyman.data.local.CustomerDao
import com.example.handyman.data.repository.CustomerRepository
import com.example.handyman.utils.Resource

class CustomerDetailViewModel @ViewModelInject constructor(
    private val customerDao: CustomerDao
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _customer = _id.switchMap { id ->
        customerDao.getCustomer(id)
    }
    // Can't resolve type mismatch for "Resource"
    val customer: Resource<LiveData<Customer>> = _customer


    fun start(id: Int) {
        _id.value = id
    }
}