package com.instagram.src.main.SignupPages

import com.instagram.config.ApplicationClass
import com.instagram.src.main.SignupPages.models.CheckuserKey
import com.instagram.src.main.SignupPages.models.CheckuserNick
import com.instagram.src.main.SignupPages.models.PostSignup
import com.instagram.src.main.SignupPages.models.Signup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupService(val signupActivityInterface: SignupActivityInterface) {

    fun tryGetCheckuserKey(query : String){
        val signupRetrofitInterface = ApplicationClass.sRetrofit.create(SignupRetrofitInterface::class.java)
        signupRetrofitInterface.getCheckuserKey(query).enqueue(object : Callback<CheckuserKey> {
            override fun onResponse(call: Call<CheckuserKey>, response: Response<CheckuserKey>) {
                signupActivityInterface.onGetCheckuserKeySuccess(response.body() as CheckuserKey)
            }

            override fun onFailure(call: Call<CheckuserKey>, t: Throwable) {
                signupActivityInterface.onGetCheckuserKeyFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetCheckuserNick(query : String){
        val signupRetrofitInterface = ApplicationClass.sRetrofit.create(SignupRetrofitInterface::class.java)
        signupRetrofitInterface.getCheckuserNick(query).enqueue(object:Callback<CheckuserNick>{
            override fun onResponse(call: Call<CheckuserNick>, response: Response<CheckuserNick>) {
                signupActivityInterface.onGetCheckuserNickSuccess(response.body() as CheckuserNick)
            }

            override fun onFailure(call: Call<CheckuserNick>, t: Throwable) {
                signupActivityInterface.onGetCheckuserNickFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostSignup(data : PostSignup){
        val signupRetrofitInterface = ApplicationClass.sRetrofit.create(SignupRetrofitInterface::class.java)
        signupRetrofitInterface.postSignup(data).enqueue(object:Callback<Signup>{
            override fun onResponse(call: Call<Signup>, response: Response<Signup>) {
                signupActivityInterface.onPostSignupSuccess(response.body() as Signup)
            }

            override fun onFailure(call: Call<Signup>, t: Throwable) {
                signupActivityInterface.onPostSignupFailure(t.message ?: "통신 오류")
            }
        })
    }


}