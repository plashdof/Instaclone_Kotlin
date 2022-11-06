package com.instagram.src.main.ShoppingPage.models

import com.google.gson.annotations.SerializedName

data class ShoppingAutocompleteData(
    @SerializedName("profile") val profile:String? = "",
    @SerializedName("nick") val nick:String? = "",
    @SerializedName("name") val name:String? = ""
)
