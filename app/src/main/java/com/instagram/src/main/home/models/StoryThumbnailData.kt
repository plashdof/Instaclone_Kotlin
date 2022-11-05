package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName

data class StoryThumbnailData(
    @SerializedName("state") var state : Boolean ? = false,
    @SerializedName("profileimage") var profile : String,
    @SerializedName("nickName") var nickName : String

)
