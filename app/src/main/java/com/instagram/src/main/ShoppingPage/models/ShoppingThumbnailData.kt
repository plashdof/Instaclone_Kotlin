package com.instagram.src.main.ShoppingPage.models

import com.google.gson.annotations.SerializedName

data class ShoppingThumbnailData(
    @SerializedName("img") val img : String?="",
    @SerializedName("id") val id : String?=""
)
