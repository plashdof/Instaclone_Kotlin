package com.instagram.src.main.Login.models

import com.google.gson.annotations.SerializedName

data class PostLoginData(
    @SerializedName("email") var email : String? = "",
    @SerializedName("phone") var phone : String? = "",
    @SerializedName("nickName") var nickName : String? = "",
    @SerializedName("password") var password : String? = ""

)
