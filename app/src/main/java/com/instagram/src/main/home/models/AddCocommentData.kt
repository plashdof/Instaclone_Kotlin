package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName

data class AddCocommentData(
    @SerializedName("postId") val postId : Int,
    @SerializedName("content") val content : String,
    @SerializedName("parentId") val parentId : Int
)
