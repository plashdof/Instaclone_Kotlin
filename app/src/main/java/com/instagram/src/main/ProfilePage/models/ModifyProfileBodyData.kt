package com.instagram.src.main.ProfilePage.models

import com.google.gson.annotations.SerializedName

data class ModifyProfileBodyData(
    @SerializedName("profileUrl") val profileUrl : String? = "",
    @SerializedName("name") val name : String? = "",
    @SerializedName("nickname") val nickname : String? = "",
    @SerializedName("description") val description : String? = "",
    @SerializedName("link") val link : String? = ""
)
