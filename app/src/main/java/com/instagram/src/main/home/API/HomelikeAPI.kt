package com.instagram.src.main.home.API

import com.instagram.src.main.home.models.PostlikeData
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface HomelikeAPI {

    @POST("/posts/like/{postid}")
    fun postLikestate(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Path("postid") postid : String? = ""
    ): Call<PostlikeData>

    @PATCH("/posts/like/{postid}")
    fun postunLikestate(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Path("postid") postid : String? = ""
    ): Call<PostlikeData>


}