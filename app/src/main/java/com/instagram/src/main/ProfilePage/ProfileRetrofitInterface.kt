package com.instagram.src.main.ProfilePage

import com.instagram.src.main.ProfilePage.models.ModifyProfileBodyData
import com.instagram.src.main.ProfilePage.models.ModifyProfileData
import com.instagram.src.main.ProfilePage.models.MyProfileData
import com.instagram.src.main.ProfilePage.models.OthersProfileData
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



}