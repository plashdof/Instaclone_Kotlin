package com.instagram.src.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("sdf","sdf")

        binding.btnLoginLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}