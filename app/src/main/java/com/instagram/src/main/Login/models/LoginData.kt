package com.instagram.src.main.Login.models

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("isSucces") var isSuccess : Boolean,
    @SerializedName("code") var code : Int,
    @SerializedName("message") var message : String,
    @SerializedName("result") var result : result

)

data class result(
    @SerializedName("userIdx") var userIdx : Int,
    @SerializedName("jwt") var jwt : String,
    @SerializedName("refreshToken") var refreshToken : String,
    @SerializedName("firstLogin") var firstLogin : String
)
