package com.sciencekom.mysecondapp.ui.inventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.sciencekom.mysecondapp.DataItem
import com.sciencekom.mysecondapp.InventoryResponse
import com.sciencekom.mysecondapp.R
import com.sciencekom.mysecondapp.databinding.ActivityInventoryListBinding
import com.sciencekom.mysecondapp.databinding.ItemInventoryBinding
import com.sciencekom.mysecondapp.remote.retrofit.ApiConfig
import com.sciencekom.mysecondapp.remote.retrofit.ItemService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryListActivity : AppCompatActivity() {

    lateinit var binding: ActivityInventoryListBinding
    lateinit var inventoryAdapter: InventoryListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()



    }

    fun getData(){
        val  client = ApiConfig.retrofit.create(ItemService::class.java).getItems()
        client.enqueue(object : Callback<InventoryResponse>{
            override fun onResponse(
                call: Call<InventoryResponse>,
                response: Response<InventoryResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body()!=null){
                        Log.d("Testing body", "onResponse: ${response.body()?.data}")
                        inventoryAdapter= InventoryListAdapter(response.body()!!.data)
                        binding.rvInventory.setHasFixedSize(true)
                        binding.rvInventory.layoutManager = LinearLayoutManager(this@InventoryListActivity)
                        binding.rvInventory.adapter =inventoryAdapter


                    }
                } else {
                    Log.d("Testing body", "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<InventoryResponse>, t: Throwable) {
                Log.e("FAIL", t.message.toString())
            }

        })


    }
}