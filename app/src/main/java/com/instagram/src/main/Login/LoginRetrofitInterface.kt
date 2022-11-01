package com.instagram.src.main.Login

import com.instagram.src.main.Login.models.LoginData
import com.instagram.src.main.Login.models.PostLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {
    @POST("/auth/logIn")
    fun postLogin(
        @Body params : PostLoginData
    ): Call<LoginData>
}