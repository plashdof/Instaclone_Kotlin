package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName

data class StoryData(
    @SerializedName("profile") val profile : String? = "",
    @SerializedName("imgData") val imgData : ArrayList<ImgData>,
    @SerializedName("nick") val nick : String? = "",
)


data class ImgData(
    @SerializedName("time") val time : String? = "",
    @SerializedName("img") val img : String? = ""
)