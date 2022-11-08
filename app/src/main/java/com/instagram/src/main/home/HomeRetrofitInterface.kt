package com.instagram.src.main.home

import com.instagram.src.main.Login.models.PostLoginData
import com.instagram.src.main.home.models.*
import retrofit2.Call
import retrofit2.http.*

interface HomeRetrofitInterface {

    @GET("/posts/home/{page}")
    fun getHomepostView(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Path("page") page : String? = ""
    ) : Call<PostData>

    @GET("/posts/like-list/{postid}")
    fun getLikelist(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Path("postid") postid : String? = ""
    ) : Call<LikelistData>

    @GET("/storys/getList")
    fun getStory(
        @Header("ACCESS-TOKEN") jwt : String? = "",
    ) : Call<StoryData>



}