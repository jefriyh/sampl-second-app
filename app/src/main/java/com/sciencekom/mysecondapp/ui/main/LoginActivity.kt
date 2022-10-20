package com.sciencekom.mysecondapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sciencekom.mysecondapp.databinding.ActivityLoginBinding
import com.sciencekom.mysecondapp.helper.Constant
import com.sciencekom.mysecondapp.helper.PrefHelper

class LoginActivity : AppCompatActivity() {

    lateinit var binding:ActivityLoginBinding
    var USERNAME = "jefri@sciencom.com"
    var PASSWORD =  "12345"

    lateinit var pref:PrefHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = PrefHelper(this)

        if(pref.getBoolean(Constant.PREF_IS_LOGIN)){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }



        binding.bLogin.setOnClickListener {

            if(binding.etEmail.text.toString()==USERNAME && binding.etPassword.text.toString()==PASSWORD){
                Toast.makeText(this,"Selamat Anda Berhasil Login", Toast.LENGTH_SHORT).show()

                pref.set(Constant.PREF_EMAIL, binding.etEmail.text.toString() )
                pref.set(Constant.PREF_IS_LOGIN, true)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this,"Mohon maaf username dan atau password Anda tidak sesuai",
                    Toast.LENGTH_SHORT).show()
            }


        }

    }
}