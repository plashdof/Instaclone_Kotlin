package com.instagram.src.main.VideoPage.models

import com.google.gson.annotations.SerializedName

data class ReelsData(
    @SerializedName("profileimg") val profileimg : String? ="",
    @SerializedName("nick") val nick : String?="",
    @SerializedName("video") val video : String?=""
)
