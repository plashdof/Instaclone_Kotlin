package com.instagram.src.main.ProfilePage

import com.instagram.src.main.ProfilePage.models.ModifyProfileBodyData
import com.instagram.src.main.ProfilePage.models.ModifyProfileData
import com.instagram.src.main.ProfilePage.models.MyProfileData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH

interface ProfileRetrofitInterface {


    @GET("profile/getMyData")
    fun getMyProfile(
        @Header("ACCESS-TOKEN") jwt : String?,
    ) : Call<MyProfileData>

    @PATCH("profile/modify")
    fun patchMyProfile(
        @Header("ACCESS-TOKEN") jwt : String?,
        @Body params : ModifyProfileBodyData
    ) : Call<ModifyProfileData>


}