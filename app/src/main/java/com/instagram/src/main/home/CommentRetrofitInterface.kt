package com.instagram.src.main.home

import com.instagram.src.main.home.models.CommentContentData
import com.instagram.src.main.home.models.CommentData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentRetrofitInterface {

    @GET("/posts/comments")
    fun getComments(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Query("postId") postId : Int,
        @Query("page") page : Int
    ) : Call<CommentData>

    @GET("/posts/detail/{postId}")
    fun getCommentContent(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Path("postId") postId : Int
    ) : Call<CommentContentData>
}