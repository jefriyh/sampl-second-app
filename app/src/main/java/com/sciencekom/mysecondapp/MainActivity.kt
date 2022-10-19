package com.sciencekom.mysecondapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sciencekom.mysecondapp.databinding.ActivityMainBinding
import com.sciencekom.mysecondapp.helper.PrefHelper

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var pref :PrefHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = PrefHelper(this)

        binding.bLogout.setOnClickListener {
            pref.clear()
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}