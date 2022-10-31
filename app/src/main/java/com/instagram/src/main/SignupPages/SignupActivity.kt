package com.instagram.src.main.SignupPages

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.instagram.R
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivitySignupBinding
import com.instagram.src.main.LoginActivity

class SignupActivity : BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate){
    var inputdata : String = ""
    var inputflag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var phonebtn = binding.btnSignupPhone
        var emailbtn = binding.btnSignupEmail
        var edittext = binding.etSignupInput
        var nextbtn = binding.btnSignupNext
        var loginbtn = binding.btnSignupLogin

        binding.btnSignupPhone.setOnClickListener {
            phonebtn.setBackgroundResource(R.drawable.shape_signupbtn_active)
            phonebtn.setTextColor(Color.BLACK)

            emailbtn.setBackgroundResource(R.drawable.shape_signupbtn)
            emailbtn.setTextColor(Color.rgb(117,117,117))

            edittext.hint = "휴대폰"
        }

        binding.btnSignupEmail.setOnClickListener {
            emailbtn.setBackgroundResource(R.drawable.shape_signupbtn_active)
            emailbtn.setTextColor(Color.BLACK)

            phonebtn.setBackgroundResource(R.drawable.shape_signupbtn)
            phonebtn.setTextColor(Color.rgb(117,117,117))

            edittext.hint = "이메일"
        }

        edittext.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                inputdata = binding.etSignupInput.text.toString()

                inputflag = inputdata.isNotBlank()

                if(inputflag){
                    nextbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
                }else{
                    nextbtn.setBackgroundResource(R.drawable.shape_loginbtn)
                }

            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        loginbtn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}