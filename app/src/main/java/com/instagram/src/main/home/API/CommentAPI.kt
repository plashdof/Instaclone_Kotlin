package com.instagram.src.main.home.API

import com.instagram.src.main.home.models.AddCocommentData
import com.instagram.src.main.home.models.PostlikeData
import retrofit2.Call
import retrofit2.http.*

interface CommentAPI {

    @POST("/posts/comments/like/{commentId}")
    fun postCommentlike(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Path("commentId") commentId : Int
    ) : Call<PostlikeData>

    @PATCH("/posts/comments/like/{commentId}")
    fun patchCommentunlike(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Path("commentId") commentId : Int
    ) : Call<PostlikeData>

    @POST("/posts/cocomments")
    fun postCocomments(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Body params : AddCocommentData,
    ) : Call<PostlikeData>
}