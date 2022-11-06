package com.instagram.src.main.home

import com.instagram.src.main.home.models.PostData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeRetrofitInterface {

    @GET("/posts/home/{page}")
    fun getHomepostView(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Path("page") page : String? = ""
    ) : Call<PostData>
}