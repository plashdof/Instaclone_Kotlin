package com.instagram.src.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.instagram.R
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate){

    var idflag = false
    var pwflag = false
    var id : String = ""
    var pw : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginbtn = binding.btnLoginLogin
        binding.etLoginId.addTextChangedListener(object:TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                id = binding.etLoginId.text.toString()

                idflag = id.isNotBlank()

                if(pwflag && idflag){
                    loginbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
                }else{
                    loginbtn.setBackgroundResource(R.drawable.shape_loginbtn)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.etLoginPw.addTextChangedListener(object:TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                pw = binding.etLoginPw.text.toString()

                pwflag = pw.isNotBlank()

                if(pwflag && idflag){
                    loginbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
                }else{
                    loginbtn.setBackgroundResource(R.drawable.shape_loginbtn)
                }

            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.btnLoginLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnLoginSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }
}