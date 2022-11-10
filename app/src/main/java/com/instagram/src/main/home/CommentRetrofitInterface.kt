package com.instagram.src.main.home

import com.instagram.src.main.home.models.*
import retrofit2.Call
import retrofit2.http.*

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

    @POST("/posts/comments")
    fun postaddComment(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Body params : AddCommentData
    ) : Call<PostlikeData>


}