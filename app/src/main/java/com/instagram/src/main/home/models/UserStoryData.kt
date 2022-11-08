package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName

data class UserStoryData(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code:Int,
    @SerializedName("message")val message:String? = "",
    @SerializedName("result") val result: UserStorydetailData
)

data class UserStorydetailData(
    @SerializedName("visitCnt") val visitCnt : Int,
    @SerializedName("storyDataList") val storyDataList : ArrayList<StorydetailData>
)
