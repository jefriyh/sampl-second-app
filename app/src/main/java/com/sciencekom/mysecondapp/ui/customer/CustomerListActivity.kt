package com.sciencekom.mysecondapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sciencekom.mysecondapp.databinding.ActivityCustomerListBinding

class CustomerListActivity : AppCompatActivity() {

    lateinit var binding:ActivityCustomerListBinding
    private val list = ArrayList<Customer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list.addAll(listCustomers)


        binding.rvCustomer.setHasFixedSize(true)
        binding.rvCustomer.layoutManager = LinearLayoutManager(this)

        val customerListAdapter = CustomerListAdapter(list)
        binding.rvCustomer.adapter = customerListAdapter

        customerListAdapter.setOnItemClickCallback(object:CustomerListAdapter.OnItemClickCallback{
            override fun onItemClicked(customer: Customer) {
                val intent = Intent(this@CustomerListActivity,
                    CustomerDetailActivity::class.java)

                Toast.makeText(this@CustomerListActivity, customer.name, Toast.LENGTH_SHORT).show()

                intent.putExtra("CUSTOMER", customer)

                startActivity(intent)


            }

        })



    }

    private val listCustomers:ArrayList<Customer> get(){
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDomicile = resources.getStringArray(R.array.data_domisili)
        val dataGender = resources.getStringArray(R.array.data_gender)
        val dataImage = resources.obtainTypedArray(R.array.data_image)

        val listCustomer = ArrayList<Customer>()

        for (i in dataName.indices){
            val customer = Customer(dataName[i],dataDomicile[i],
                dataGender[i], dataImage.getResourceId(i, 0))
            listCustomer.add(customer)
        }
        return listCustomer
    }
}