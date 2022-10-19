package com.sciencekom.mysecondapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sciencekom.mysecondapp.databinding.ItemCustomerBinding

class CustomerListAdapter(var listItem:ArrayList<Customer>) :
    RecyclerView.Adapter<CustomerListAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemCustomerBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCustomerBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val customerData = listItem[position]

        holder.binding.tvCustomerName.text =customerData.name
        holder.binding.tvCustomerDomisili.text = customerData.domicile
        holder.binding.tvCustomerGender.text = customerData.gender
        holder.binding.ivCustomerImage.setImageResource(customerData.image)
        holder.binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(listItem[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return  listItem.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(customer: Customer)
    }

    private lateinit var onItemClickCallback : OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }


}