package com.instagram.src.main.ProfilePage.models

import com.google.gson.annotations.SerializedName

data class OthersProfileData(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String?="",
    @SerializedName("result") val result : OthersProfiledetailData
)

data class OthersProfiledetailData(
    @SerializedName("profileUrl") val profileUrl : String?="",
    @SerializedName("name") val name : String?="",
    @SerializedName("nickname") val nickname : String?="",
    @SerializedName("description") val description : String?="",
    @SerializedName("link") val link : String?="",
    @SerializedName("postCount") val postCount : Int,
    @SerializedName("followerCount") val followerCount : Int,
    @SerializedName("followingCount") val followingCount : Int,
    @SerializedName("followingStatus") val followingStatus : String?="",
    @SerializedName("followTogetherList") val followTogetherList : ArrayList<FollowTogetherData>
)

data class FollowTogetherData(
    @SerializedName("userId") val userId : Int,
    @SerializedName("profileUrl") val profileUrl : String?="",
    @SerializedName("nickname") val nickname : String?=""
)