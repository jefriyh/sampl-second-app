package com.sciencekom.mysecondapp.ui.customer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sciencekom.mysecondapp.databinding.ActivityCustomerDetailBinding

class CustomerDetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCustomerDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val customer = intent.getParcelableExtra<Customer>("CUSTOMER")!!
        binding.tvName.text = customer.name
        binding.tvCustomerGender.text = customer.gender
        binding.tvCustomerDomisili.text = customer.domicile
        binding.ivCustomerImage.setImageResource(customer.image)
    }
}