package com.instagram.src.main.SignupPages

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.instagram.R
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivitySignuppwBinding

class SignuppwActivity : BaseActivity<ActivitySignuppwBinding>(ActivitySignuppwBinding::inflate){

    var name : String = ""
    var pw : String = ""
    var nameflag = false
    var pwflag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.none, R.anim.none)

        var userKey = intent.getStringExtra("userKey")

        val nextbtn = binding.btnSignuppwNext

        nextbtn.isClickable = false

        binding.etSignuppwName.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                name = binding.etSignuppwName.text.toString()

                nameflag = name.isNotBlank()

                Log.d("flag", "$pwflag $nameflag")

                if(pwflag && nameflag){
                    nextbtn.isClickable = true
                    nextbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
                }else{
                    nextbtn.isClickable = false
                    nextbtn.setBackgroundResource(R.drawable.shape_loginbtn)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.etSignuppwPw.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                pw = binding.etSignuppwPw.text.toString()

                pwflag = pw.isNotBlank()

                Log.d("flag", "$pwflag $nameflag")

                if(pwflag && nameflag){
                    nextbtn.isClickable = true
                    nextbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
                }else{
                    nextbtn.isClickable = false
                    nextbtn.setBackgroundResource(R.drawable.shape_loginbtn)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        nextbtn.setOnClickListener {
            val intent = Intent(this, SignupbirthActivity::class.java)
                .putExtra("name",name)
                .putExtra("pw", pw)
                .putExtra("userKey", userKey)
            startActivity(intent)
        }
    }
}