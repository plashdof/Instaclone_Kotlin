package com.instagram.src.main.SearchPage.models

import com.google.gson.annotations.SerializedName

data class SearchThumbnailData(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String?="",
    @SerializedName("result") val result : ArrayList<SearchImgList>
)

data class SearchImgList(
    @SerializedName("postId") val postId : Int,
    @SerializedName("imgUrl") val imgUrl : String
)