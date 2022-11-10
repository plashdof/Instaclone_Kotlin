package com.instagram.src.main.ProfilePage.models

import com.google.gson.annotations.SerializedName
import com.instagram.src.main.home.models.ImgData

data class UserPostListData(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String? = "",
    @SerializedName("result") val result : UserPostListdetailData
)

data class UserPostListdetailData(
    @SerializedName("page") val page: Int,
    @SerializedName("numOfPosts") val numOfPosts : Int,
    @SerializedName("postList") val postList : ArrayList<UserPostListdetaildetailData>
)

data class UserPostListdetaildetailData(
    @SerializedName("postId") val postId : Int,
    @SerializedName("userId") val userId : Int,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("userImg") val userImg : String,
    @SerializedName("content") val content : String,
    @SerializedName("reels") val reels : String,
    @SerializedName("reelsMusic") val reelsMusic : String,
    @SerializedName("time") val time : String,
    @SerializedName("likeCount") val likeCount : Int,
    @SerializedName("commentCount") val commentCount : Int,
    @SerializedName("myPostLike") val myPostLike : Int,
    @SerializedName("storyExist") val storyExist : String,
    @SerializedName("imgUrlList") val imgUrlList : ArrayList<ImgData>,
    @SerializedName("userTagList") val userTagLIst : ArrayList <Int>,
    @SerializedName("hashTagList") val hashTagList : ArrayList<String>

)

