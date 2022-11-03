package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName

data class PostData(
    @SerializedName("imgdata") val imgdata : Array<String>,
    @SerializedName("nick") val nick : String? = "",
    @SerializedName("profileimg") val profileimg : String? = "",
    @SerializedName("like") val like : Int? = null
)



