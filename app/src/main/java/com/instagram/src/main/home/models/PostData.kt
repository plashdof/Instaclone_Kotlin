package com.instagram.src.main.home.models

import com.google.gson.annotations.SerializedName

data class PostData(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code:Int,
    @SerializedName("message")val message:String? = "",
    @SerializedName("result") val result: ResultData
)

data class ResultData(
    @SerializedName("page") val page : Int,
    @SerializedName("numOfPosts") val numOfPosts : Int,
    @SerializedName("postList") val postList : ArrayList<PostdetialData>

)

data class PostdetialData(
    @SerializedName("postId") val postId : Int,
    @SerializedName("userId") val userId : Int,
    @SerializedName("nickname") val nickname : String? ="",
    @SerializedName("userImg") val userImg : String? ="",
    @SerializedName("content") val content : String? ="",
    @SerializedName("reels") val reels : String? ="",
    @SerializedName("reelsMusic") val reelsMusic : String? ="",
    @SerializedName("time") val time : String? ="",
    @SerializedName("likeCount") val likeCount : Int,
    @SerializedName("commentCount") val commentCount : Int,
    @SerializedName("myPostLike") val myPostLike : Int,
    @SerializedName("storyExist") val storyExist : String? ="",
    @SerializedName("imgUrlList") val imgUrlList : ArrayList<String?>,
    @SerializedName("userTagList") val userTagList : ArrayList<Int?>,
    @SerializedName("hashTagList") val hashTagList : ArrayList<String?>

)



