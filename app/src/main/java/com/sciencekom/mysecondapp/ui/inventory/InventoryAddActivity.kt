package com.sciencekom.mysecondapp.ui.inventory

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.sciencekom.mysecondapp.DataItem
import com.sciencekom.mysecondapp.R
import com.sciencekom.mysecondapp.databinding.ActivityInventoryAddBinding
import com.sciencekom.mysecondapp.remote.response.InventoryAddResponse
import com.sciencekom.mysecondapp.remote.retrofit.ApiConfig
import com.sciencekom.mysecondapp.remote.retrofit.ItemService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityInventoryAddBinding
    var inventory: DataItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inventory = intent.getParcelableExtra<DataItem>("INVENTORY")

        if(inventory!=null){
            binding.tvJudul.text = "Ubah Inventory"
            binding.etName.setText(inventory!!.name)
            binding.etCategory.setText(inventory!!.category)
            binding.etPrice.setText(inventory!!.price)
            binding.etColor.setText(inventory!!.color)
        }

        binding.bSubmit.setOnClickListener {

            if(binding.etName.text.toString()!="" &&
                binding.etColor.text.toString()!="" &&
                binding.etCategory.text.toString()!="" &&
                binding.etPrice.text.toString()!=""){
                if(inventory!=null){
                    updateItemProcess()
                }
                else{
                    postItemProcess()
                }
            }
            else{
                Toast.makeText(this,"Mohon Pastikan Semua Form Telah Terisi",
                    Toast.LENGTH_SHORT).show()
            }

        }


    }

    fun postItemProcess(){
        binding.pbLoading.visibility = View.VISIBLE
        val client = ApiConfig.retrofit.create(ItemService::class.java).postItems(
            binding.etName.text.toString(), binding.etCategory.text.toString(),
            binding.etColor.text.toString(),  binding.etPrice.text.toString().toInt()
        )

        client.enqueue(object : Callback<InventoryAddResponse>{
            override fun onResponse(
                call: Call<InventoryAddResponse>,
                response: Response<InventoryAddResponse>
            ) {
                binding.pbLoading.visibility = View.GONE
                if(response.isSuccessful){
                    if(response.body()!=null){
                        Log.d("SUCCESS", "Data berhasil ditambah")
                        Toast.makeText(this@InventoryAddActivity,
                            "Data Barang Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                else{
                    Log.e("FAIL", "Data gagal ditambahkan")
                    Toast.makeText(this@InventoryAddActivity,
                        "Data Barang Gagal Ditambahkan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<InventoryAddResponse>, t: Throwable) {
                binding.pbLoading.visibility = View.GONE
                Log.e("FAIL", t.message.toString())
                Toast.makeText(this@InventoryAddActivity,
                    "Data Barang Gagal Ditambahkan", Toast.LENGTH_SHORT).show()
            }

        })



    }



    fun updateItemProcess(){
        binding.pbLoading.visibility = View.VISIBLE
        val client = ApiConfig.retrofit.create(ItemService::class.java).updateItems(
            inventory!!.idItem,
            binding.etName.text.toString(), binding.etCategory.text.toString(),
            binding.etColor.text.toString(),  binding.etPrice.text.toString().toInt()
        )

        client.enqueue(object : Callback<InventoryAddResponse>{
            override fun onResponse(
                call: Call<InventoryAddResponse>,
                response: Response<InventoryAddResponse>
            ) {
                binding.pbLoading.visibility = View.GONE
                if(response.isSuccessful){
                    if(response.body()!=null){
                        Log.d("SUCCESS", "Data berhasil diubah")
                        Toast.makeText(this@InventoryAddActivity,
                            "Data Barang Berhasil Diubah", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                else{
                    Log.e("FAIL", "Data gagal diubah")
                    Toast.makeText(this@InventoryAddActivity,
                        "Data Barang Gagal Diubah", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<InventoryAddResponse>, t: Throwable) {
                binding.pbLoading.visibility = View.GONE
                Log.e("FAIL", t.message.toString())
                Toast.makeText(this@InventoryAddActivity,
                    "Data Barang Gagal Ditambahkan", Toast.LENGTH_SHORT).show()
            }

        })



    }


}