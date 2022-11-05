package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName

data class StoryData(
    @SerializedName("profile") val profile : String? = "",
    @SerializedName("imglist") val imglist : ArrayList<ImgList>,
    @SerializedName("nick") val nick : String? = "",
)


data class ImgList(
    @SerializedName("time") val time : String? = "",
    @SerializedName("img") val img : String? = ""
)