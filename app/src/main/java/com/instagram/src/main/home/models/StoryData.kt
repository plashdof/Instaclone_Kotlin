package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StoryData(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code:Int,
    @SerializedName("message")val message:String? = "",
    @SerializedName("result") val result: ArrayList<StorythumbnailData>
)


data class StorythumbnailData(
    @SerializedName("nickname") val nickname : String? = "",
    @SerializedName("profileUrl") val profileUrl : String? = "",
    @SerializedName("visitCnt") val visitCnt : Int,
    @SerializedName("storyDataList") val storyDataList : ArrayList<StorydetailData>
) : Serializable

data class StorydetailData(
    @SerializedName("storyId") val storyId : Int,
    @SerializedName("imgUrl") val imgUrl : String? = "",
    @SerializedName("createdAt") val createdAt : Int,
    @SerializedName("userTagList") val userTagList : ArrayList<TagList>
) : Serializable

data class TagList(
    @SerializedName("nickname") val nickname : String? = ""
) : Serializable