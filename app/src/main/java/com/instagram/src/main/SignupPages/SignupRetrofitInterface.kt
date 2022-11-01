package com.instagram.src.main.SignupPages

import com.instagram.src.main.SignupPages.models.CheckuserKey
import com.instagram.src.main.SignupPages.models.CheckuserNick
import com.instagram.src.main.SignupPages.models.PostSignup
import com.instagram.src.main.SignupPages.models.Signup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignupRetrofitInterface{
    @GET("/users/check/id")
    fun getCheckuserKey(
        @Query("userKey") userKey : String,
    ) : Call<CheckuserKey>

    @GET("/users/check/nick")
    fun getCheckuserNick(
        @Query("nickName") nickName : String
    ) : Call<CheckuserNick>

    @POST("/users/signUp")
    fun postSignup(
        @Body params : PostSignup
    ): Call<Signup>
}

