package com.instagram.src.main.SignupPages.models

import com.google.gson.annotations.SerializedName

data class PostSignup(
    @SerializedName("name") var name : String? = "",
    @SerializedName("nickname") var nickname : String? = "",
    @SerializedName("password") var password : String? = "",
    @SerializedName("userKey") var userKey : String? = "",
    @SerializedName("birth") var birth : String? = ""
)