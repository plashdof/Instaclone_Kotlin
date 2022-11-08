package com.instagram.src.main.home

import com.instagram.src.main.home.models.MakeStoryResponseData
import com.instagram.src.main.home.models.PostMakeStoryData
import com.instagram.src.main.home.models.UserStoryData
import retrofit2.Call
import retrofit2.http.*

interface StoryRetrofitInterface {

    @POST("/storys/create")
    fun postMakeStory(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Body params : PostMakeStoryData
    ) : Call<MakeStoryResponseData>

    @GET("/storys/getUserStory")
    fun getUserStory(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Query("targetUser") targetUser : String? = ""
    ) : Call<UserStoryData>
}