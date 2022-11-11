package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName

data class MakePostData(
    @SerializedName("content") val content : String? = "",
    @SerializedName("imgUrlList") val imgUrlList : ArrayList<String>,
    @SerializedName("reels") val reels : String?="N",
    @SerializedName("reelsMusic") val reelsMusic : String?=null,
    @SerializedName("userTagList") val userTagList : ArrayList<Int>,
    @SerializedName("hashTagList") val hasTagList : ArrayList<String>,
)
