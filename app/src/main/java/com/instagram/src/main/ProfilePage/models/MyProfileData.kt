package com.instagram.src.main.ProfilePage.models

import com.google.gson.annotations.SerializedName

data class MyProfileData(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : Result
)

data class Result(
    @SerializedName("userIdx") val userIdx : Int,
    @SerializedName("profileUrl") val profileUrl : String,
    @SerializedName("name") val name : String,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("description") val description : String,
    @SerializedName("link") val link : String,
    @SerializedName("postCount") val postCount : Int,
    @SerializedName("followerCount") val followerCount : Int,
    @SerializedName("followingCount") val followingCount : Int
)
