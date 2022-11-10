package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName

data class AddCommentData (
    @SerializedName("postId") val postId : Int,
    @SerializedName("content") val content : String
)