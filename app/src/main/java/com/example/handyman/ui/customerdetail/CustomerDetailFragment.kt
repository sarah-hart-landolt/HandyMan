package com.example.handyman.ui.customerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.handyman.data.entities.Character
import com.example.handyman.data.entities.Customer
import com.example.handyman.databinding.CustomerDetailFragmentBinding
import com.example.handyman.ui.characterdetail.CustomerDetailViewModel
import com.example.handyman.utils.Resource
import com.example.handyman.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerDetailFragment : Fragment() {

    private var binding: CustomerDetailFragmentBinding by autoCleared()
    private val viewModel: CustomerDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CustomerDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
/*
        setupObservers()
*/
    }
           /* private fun setupObservers() {
                viewModel.customer.observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            bindCustomer(it.data!!)
                            binding.progressBar.visibility = View.GONE
                            binding.customerCl.visibility = View.VISIBLE
                        }

                        Resource.Status.ERROR ->
                            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                        Resource.Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.customerCl.visibility = View.GONE
                        }
                    }
                })
            }*/

    private fun bindCustomer(customer: Customer) {
        binding.name.text = customer.name
        binding.phone.text = customer.phoneNumber
        binding.address.text = customer.location.address.street
        binding.service.text = customer.serviceReason
        Glide.with(binding.root)
            .load(customer.profilePicture.large)
            .transform(CircleCrop())
            .into(binding.image)
    }
}
