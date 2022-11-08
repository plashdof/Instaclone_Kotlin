package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName

data class PostMakeStoryData(
    @SerializedName("storyImgUrl") val storyImgUrl : String? = "",
    @SerializedName("userTagList") val userTagList: ArrayList<Nicknames>? = null
)

data class Nicknames(
    @SerializedName("nickname") val nickname : String? = ""
)
