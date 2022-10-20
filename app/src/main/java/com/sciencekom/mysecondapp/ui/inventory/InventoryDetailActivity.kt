package com.sciencekom.mysecondapp.ui.inventory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sciencekom.mysecondapp.DataItem
import com.sciencekom.mysecondapp.R
import com.sciencekom.mysecondapp.databinding.ActivityInventoryDetailBinding
import com.sciencekom.mysecondapp.remote.response.InventoryAddResponse
import com.sciencekom.mysecondapp.remote.retrofit.ApiConfig
import com.sciencekom.mysecondapp.remote.retrofit.ItemService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityInventoryDetailBinding
    lateinit var inventory:DataItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inventory = intent.getParcelableExtra<DataItem>("INVENTORY")!!

        binding.tvInventoryName.text = inventory.name
        binding.tvInventoryCategory.text = inventory.category
        binding.tvInventoryColor.text = inventory.color
        binding.tvInventoryPrice.text = inventory.price

        Glide.with(this)
            .load(inventory.image)
            .centerCrop()
            .into(binding.ivInventoryImage)

        binding.bDelete.setOnClickListener {
                deleteProcess()
        }

        binding.bEdit.setOnClickListener {
            val intent = Intent(this,InventoryAddActivity::class.java)
            intent.putExtra("INVENTORY", inventory)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        finish()
    }

    fun deleteProcess(){
        binding.pbLoading.visibility = View.VISIBLE
        val client = ApiConfig.retrofit.create(ItemService::class.java).deleteItems(inventory.idItem)

        client.enqueue(object : Callback<InventoryAddResponse>{
            override fun onResponse(
                call: Call<InventoryAddResponse>,
                response: Response<InventoryAddResponse>
            ) {
                if(response.isSuccessful) {
                    binding.pbLoading.visibility = View.GONE
                    Toast.makeText(
                        this@InventoryDetailActivity,
                        "Data berhasil dihapus", Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }else{
                    Toast.makeText(
                        this@InventoryDetailActivity,
                        "Data gagal dihapus", Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<InventoryAddResponse>, t: Throwable) {
                binding.pbLoading.visibility = View.GONE
                Toast.makeText(
                    this@InventoryDetailActivity,
                    "Data gagal dihapus", Toast.LENGTH_SHORT
                ).show()
            }

        })
    }
}