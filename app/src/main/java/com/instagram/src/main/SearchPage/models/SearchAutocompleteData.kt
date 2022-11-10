package com.instagram.src.main.SearchPage.models

import com.google.gson.annotations.SerializedName

data class SearchAutocompleteData(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String?="",
    @SerializedName("result") val result : ArrayList<Autocompletelist>
)

data class Autocompletelist(
    @SerializedName("userId") val userId : Int,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("name") val name : String,
    @SerializedName("profileUrl") val profileUrl : String,
    @SerializedName("storyStatus") val storyStatus : String
)
