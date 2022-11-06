package com.instagram.src.main.home

import com.instagram.src.main.home.models.PostData
import com.instagram.src.main.home.models.PostlikeData
import retrofit2.Call
import retrofit2.http.*

interface HomeRetrofitInterface {

    @GET("/posts/home/{page}")
    fun getHomepostView(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Path("page") page : String? = ""
    ) : Call<PostData>


}