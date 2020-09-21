package com.example.handyman.ui.customers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.handyman.data.entities.Customer
import com.example.handyman.databinding.ItemCharacterBinding


class CustomersAdapter(private val listener: CustomerItemListener) : RecyclerView.Adapter<CustomerViewHolder>() {

    interface CustomerItemListener {
        fun onClickedCustomer(customerId: Int)
    }

    private val items = ArrayList<Customer>()

    fun setItems(items: ArrayList<Customer>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding: ItemCharacterBinding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) = holder.bind(items[position])
}

class CustomerViewHolder(private val itemBinding: ItemCharacterBinding, private val listener: CustomersAdapter.CustomerItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var customer: Customer

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Customer) {
        this.customer = item
        itemBinding.name.text = item.name
        itemBinding.info.text = """${item.phoneNumber} - visit order ${item.visitOrder}"""
        itemBinding.address.text= item.location.address.city
        Glide.with(itemBinding.root)
            .load(item.profilePicture.thumbnail)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onClickedCustomer(customer.identifier)
    }
}

