package com.instagram.src.main.ProfilePage

import com.instagram.src.main.ProfilePage.models.*
import retrofit2.Call
import retrofit2.http.*

interface ProfileRetrofitInterface {


    @GET("profile/getMyData")
    fun getMyProfile(
        @Header("ACCESS-TOKEN") jwt : String?,
    ) : Call<MyProfileData>

    @GET("profile/getData")
    fun getOthersProfile(
        @Header("ACCESS-TOKEN") jwt : String?,
        @Query("targetNickname") nickname : String?
    ) : Call<OthersProfileData>

    @PATCH("profile/modify")
    fun patchMyProfile(
        @Header("ACCESS-TOKEN") jwt : String?,
        @Body params : ModifyProfileBodyData
    ) : Call<ModifyProfileData>

    @POST("/follows/follow")
    fun postFollows(
        @Header("ACCESS-TOKEN") jwt : String?,
        @Query("targetId") targetId : Int
    ) : Call<PostFollowingData>

    @PATCH("/follows/unFollow")
    fun patchunFollow(
        @Header("ACCESS-TOKEN") jwt : String?,
        @Query("targetId") targetId : Int
    ): Call<PostFollowingData>

}