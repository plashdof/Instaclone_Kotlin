package com.instagram.src.main.ProfilePage

import com.instagram.src.main.ProfilePage.models.MyProfileData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileRetrofitInterface {


    @GET("profile/getMyData")
    fun getMyProfile(
        @Header("ACCESS-TOKEN") jwt : String?,
    ) : Call<MyProfileData>


}