package com.example.handyman.ui.customers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.handyman.R
import com.example.handyman.databinding.CustomersFragmentBinding
import com.example.handyman.utils.Resource
import com.example.handyman.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomersFragment : Fragment(), CustomersAdapter.CustomerItemListener {

    private var binding: CustomersFragmentBinding by autoCleared()
    private val viewModel: CustomersViewModel by viewModels()
    private lateinit var adapter: CustomersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CustomersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = CustomersAdapter(this)
        binding.customersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.customersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.customers.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedCustomer(customerId: Int) {
        findNavController().navigate(
            R.id.action_customersFragment_to_customerDetailFragment,
            bundleOf("id" to customerId)
        )
    }
}
