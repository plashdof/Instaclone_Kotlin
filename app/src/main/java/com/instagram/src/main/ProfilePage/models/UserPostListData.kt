package com.instagram.src.main.ProfilePage.models

import com.google.gson.annotations.SerializedName
import com.instagram.src.main.home.models.ImgData
import com.instagram.src.main.home.models.PostdetialData

data class UserPostListData(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String? = "",
    @SerializedName("result") val result : UserPostListdetailData
)

data class UserPostListdetailData(
    @SerializedName("page") val page: Int,
    @SerializedName("numOfPosts") val numOfPosts : Int,
    @SerializedName("postList") val postList : ArrayList<PostdetialData>
)



