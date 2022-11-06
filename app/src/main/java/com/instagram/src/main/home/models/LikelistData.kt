package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName


data class LikelistData(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String?="",
    @SerializedName("result") val result : ArrayList<LikelistdetialData>
)

data class LikelistdetialData(
    @SerializedName("postId") val postId : Int,
    @SerializedName("userId") val userId : Int,
    @SerializedName("userImg") val userImg : String?="",
    @SerializedName("name") val name : String? ="",
    @SerializedName("nickname") val nickname : String? ="",
    @SerializedName("storyExist") val storyExit : String?="",
    @SerializedName("followState") val followState : String?=""
)