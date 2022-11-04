package com.instagram.src.main.SearchPage.models

import com.google.gson.annotations.SerializedName

data class SearchThumbnailData(
    @SerializedName("img") val img : String? = "",
    @SerializedName("id") val id : String? = ""
)