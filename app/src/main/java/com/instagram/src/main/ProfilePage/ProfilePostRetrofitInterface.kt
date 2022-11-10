package com.instagram.src.main.ProfilePage

import com.instagram.src.main.ProfilePage.models.UserPostListData
import com.instagram.src.main.ProfilePage.models.UserPostThumbnailData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProfilePostRetrofitInterface {
    @GET("/posts/user/thumbnail")
    fun getUserPostThumbnail(
        @Header("ACCESS-TOKEN") jwt : String?,
        @Query("userId") userId : Int,
        @Query("page") page : Int
    ) : Call<UserPostThumbnailData>

    @GET("/posts/user/list")
    fun getUserPostList(
        @Header("ACCESS-TOKEN") jwt : String?,
        @Query("userId") userId : Int,
        @Query("page") page : Int
    ) : Call<UserPostListData>

    @GET("posts/user/tag")
    fun getUserTaggedThumbnail(
        @Header("ACCESS-TOKEN") jwt : String?,
        @Query("userId") userId : Int,
        @Query("page") page : Int
    ): Call<UserPostThumbnailData>

    @GET("posts/user/tag/list")
    fun getUserTaggedList(
        @Header("ACCESS-TOKEN") jwt : String?,
        @Query("userId") userId : Int,
        @Query("postId") postId : Int,
        @Query("page") page : Int
    ) : Call<UserPostListData>

}