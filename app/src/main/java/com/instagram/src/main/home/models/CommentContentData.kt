package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName

data class CommentContentData(

    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String?="",
    @SerializedName("result") val result : CommentContentDetail

)

data class CommentContentDetail(
    @SerializedName("postId") val postId : Int,
    @SerializedName("userId") val userId : Int,
    @SerializedName("userImg") val userImg : String,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("storyExist") val storyExist : String,
    @SerializedName("content") val content : String,
    @SerializedName("time") val time : String,
    @SerializedName("modifyCheck") val modifyCheck : String
)
