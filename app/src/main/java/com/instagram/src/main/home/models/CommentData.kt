package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName

data class CommentData(

    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String?="",
    @SerializedName("result") val result : CommentInfoData
)

data class CommentInfoData(
    @SerializedName("postId") val postId : Int,
    @SerializedName("page") val page : Int,
    @SerializedName("numOfComments") val numOfComments : Int,
    @SerializedName("commentList") val commentList : ArrayList<CommentdetailData>
)

data class CommentdetailData(
    @SerializedName("postId") val postId : Int,
    @SerializedName("commentId") val commentId : Int,
    @SerializedName("userId") val userId : Int,
    @SerializedName("userImg") val userImg : String,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("content") val content : String,
    @SerializedName("parentId") val parentId : Int,
    @SerializedName("time") val time : String,
    @SerializedName("storyExist") val storyExist : String,
    @SerializedName("likeCount") val likeCount : Int,
    @SerializedName("commentNum") val commentNum : Int,
    @SerializedName("myLike") val myLike : Int
)
