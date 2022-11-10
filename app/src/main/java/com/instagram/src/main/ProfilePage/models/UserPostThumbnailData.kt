package com.instagram.src.main.ProfilePage.models

import com.google.gson.annotations.SerializedName

data class UserPostThumbnailData(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String? = "",
    @SerializedName("result") val result : UserPostThumbnaildetailData
)

data class UserPostThumbnaildetailData(
    @SerializedName("userId") val userId : Int,
    @SerializedName("page") val page : Int,
    @SerializedName("numOfPosts") val numOfPosts : Int,
    @SerializedName("thumbnailList") val thumbnailList : ArrayList<PostDataThumbnailList>
)

data class PostDataThumbnailList(
    @SerializedName("postId") val postId : Int,
    @SerializedName("thumbnail") val thumbnail : String?=""
)
