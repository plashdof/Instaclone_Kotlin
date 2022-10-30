package com.instagram.src.main.home

import com.instagram.src.main.home.models.PostSignUpRequest
import com.instagram.src.main.home.models.SignUpResponse
import com.instagram.src.main.home.models.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface HomeRetrofitInterface {
    @GET("/template/users")
    fun getUsers() : Call<UserResponse>

    @POST("/template/users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>
}
