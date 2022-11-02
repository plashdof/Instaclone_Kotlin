package com.instagram.src.main.SignupPages

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import com.instagram.R
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivitySignupnickBinding
import com.instagram.src.main.Login.LoginActivity
import com.instagram.src.main.MainActivity
import com.instagram.src.main.SignupPages.models.CheckuserKey
import com.instagram.src.main.SignupPages.models.CheckuserNick
import com.instagram.src.main.SignupPages.models.PostSignup
import com.instagram.src.main.SignupPages.models.Signup

class SignupnickActivity :BaseActivity<ActivitySignupnickBinding>(ActivitySignupnickBinding::inflate),SignupActivityInterface{

    var nickname : String = ""
    var nickflag = false

    private lateinit var nextbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.none, R.anim.none)

        nextbtn = binding.btnSignupnickNext
        nextbtn.isClickable =false
        
        val name = intent.getStringExtra("name")
        val pw = intent.getStringExtra("pw")
        val birth = intent.getStringExtra("birth")
        val userKey = intent.getStringExtra("userKey")

        binding.etSignupnickName.addTextChangedListener(object:TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nickname = binding.etSignupnickName.text.toString()

                nextbtn.isClickable =false
                nextbtn.setBackgroundResource(R.drawable.shape_loginbtn)

                SignupService(this@SignupnickActivity).tryGetCheckuserNick(nickname)
            }

            override fun afterTextChanged(p0: Editable?) { }
        })
        
        
        nextbtn.setOnClickListener {

            Log.d("result:", "$name $pw $birth $nickname $userKey")
            val data = PostSignup(name = name, password = pw, birth = birth,
            nickname = nickname, userKey = userKey)

            SignupService(this).tryPostSignup(data)
        }
    }

    override fun onGetCheckuserNickSuccess(response: CheckuserNick) {
        if(response.result == "사용가능한 닉네임입니다."){
            nextbtn.isClickable =true
            nextbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
        }else{
            nextbtn.isClickable =false
            nextbtn.setBackgroundResource(R.drawable.shape_loginbtn)
        }
    }

    override fun onGetCheckuserNickFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPostSignupSuccess(response: Signup) {

        if(response.isSuccess){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPostSignupFailure(message: String) {
        Log.d("ddddd", message)
    }

    override fun onGetCheckuserKeySuccess(response: CheckuserKey) {
        TODO("Not yet implemented")
    }

    override fun onGetCheckuserKeyFailure(message: String) {
        TODO("Not yet implemented")
    }


}