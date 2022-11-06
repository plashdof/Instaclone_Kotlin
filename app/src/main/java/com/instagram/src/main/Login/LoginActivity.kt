package com.instagram.src.main.Login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.instagram.R
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivityLoginBinding
import com.instagram.src.main.Login.models.LoginData
import com.instagram.src.main.Login.models.PostLoginData
import com.instagram.src.main.MainActivity
import com.instagram.src.main.SignupPages.SignupActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LoginActivityInterface{

    var idflag = false
    var pwflag = false
    var id : String = ""
    var pw : String = ""

    var emailflag = false
    var nickflag = false
    var phoneflag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginbtn = binding.btnLoginLogin
        loginbtn.isClickable = false

        Log.d("aaaaa","onCreate")



        binding.etLoginId.addTextChangedListener(object:TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                id = binding.etLoginId.text.toString()

                idflag = id.isNotBlank()

                if(pwflag && idflag){
                    loginbtn.isClickable = true
                    loginbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
                }else{
                    loginbtn.isClickable = false
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
                    loginbtn.isClickable = true
                    loginbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
                }else{
                    loginbtn.isClickable = false
                    loginbtn.setBackgroundResource(R.drawable.shape_loginbtn)
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })


        binding.btnLoginSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()

        binding.btnLoginLogin.setOnClickListener {
            val size = id.length

            // 이메일인지 확인
            for(i in 0 until size){
                if(id[i] == '@'){
                    emailflag = true
                }
            }

            // nickname 인지 확인
            for(i in 0 until size){
                if(id[i] - '0' < 0 || id[i] - '0' > 9){
                    nickflag = true
                    phoneflag = false
                }
            }

            if(emailflag){
                val data = PostLoginData(email = id, password = pw)

                LoginService(this).tryPostLogin(data)
            }else if(nickflag){
                val data = PostLoginData(nickName = id, password = pw)
                LoginService(this).tryPostLogin(data)
            }else{
                val data = PostLoginData(phone = id, password = pw)
                LoginService(this).tryPostLogin(data)
            }
        }
    }

    override fun onPostLoginSuccess(response: LoginData) {
        if(response.code == 1000){
            showCustomToast("로그인 성공")



            val jwt = response.result.jwt

            val intent = Intent(this, MainActivity::class.java)
                .putExtra("jwt",jwt)
            startActivity(intent)
        }

    }

    override fun onPostLoginFailure(message: String) {
        showCustomToast("로그인 실패")
    }
}