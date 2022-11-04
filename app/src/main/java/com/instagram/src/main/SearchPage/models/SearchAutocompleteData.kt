package com.instagram.src.main.SearchPage.models

import com.google.gson.annotations.SerializedName

data class SearchAutocompleteData(
    @SerializedName("profile") val profile:String? = "",
    @SerializedName("nick") val nick:String? = "",
    @SerializedName("name") val name:String? = ""
)
