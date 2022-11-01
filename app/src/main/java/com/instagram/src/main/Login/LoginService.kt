package com.instagram.src.main.Login

import com.instagram.config.ApplicationClass
import com.instagram.src.main.Login.models.LoginData
import com.instagram.src.main.Login.models.PostLoginData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val loginActivityInterface: LoginActivityInterface) {
    fun tryPostLogin(data : PostLoginData){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postLogin(data).enqueue(object: Callback<LoginData>{
            override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                loginActivityInterface.onPostLoginSuccess(response.body() as LoginData)
            }

            override fun onFailure(call: Call<LoginData>, t: Throwable) {
                loginActivityInterface.onPostLoginFailure(t.message ?: "통신오류")
            }
        })
    }
}