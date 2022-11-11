package com.instagram.src.main.home.API

import com.instagram.src.main.home.models.MakePostData
import com.instagram.src.main.home.models.PostlikeData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface MakePostAPI {

    @POST("/posts")
    fun makePost(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Body params : MakePostData
    ) : Call<PostlikeData>
}