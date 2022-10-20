package com.sciencekom.mysecondapp.ui.inventory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.sciencekom.mysecondapp.DataItem
import com.sciencekom.mysecondapp.InventoryResponse
import com.sciencekom.mysecondapp.databinding.ItemCustomerBinding
import com.sciencekom.mysecondapp.databinding.ItemInventoryBinding

class InventoryListAdapter(var listItem:List<DataItem>) :
    RecyclerView.Adapter<InventoryListAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemInventoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInventoryBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val inventoryData = listItem[position]

        holder.binding.tvInventoryName.text = inventoryData.name
        holder.binding.tvInventoryCategory.text = inventoryData.category
        holder.binding.tvInventoryPrice.text = inventoryData.price


        Glide.with(holder.binding.root)
            .load("https://indi.tech/wp-content/uploads/2019/05/unnamed-Copy-2-1-1.png")
            .centerCrop()
            .into(holder.binding.ivInventoryImage)

        holder.binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(listItem[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return  listItem.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(inventory: DataItem)
    }

    private lateinit var onItemClickCallback : OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }


}