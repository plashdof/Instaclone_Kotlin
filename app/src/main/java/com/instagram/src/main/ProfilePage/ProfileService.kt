package com.instagram.src.main.ProfilePage

import com.instagram.config.ApplicationClass
import com.instagram.src.main.ProfilePage.models.MyProfileData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileService(val profileActivityInterface: ProfileActivityInterface) {

    fun tryGetMyProfileData(jwt : String?){
        val profileRetrofitInterface = ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
        profileRetrofitInterface.getMyProfile(jwt).enqueue(object: Callback<MyProfileData>{
            override fun onResponse(call: Call<MyProfileData>, response: Response<MyProfileData>) {
                profileActivityInterface.onGetMyProfileSuccess(response.body() as MyProfileData)
            }

            override fun onFailure(call: Call<MyProfileData>, t: Throwable) {
                profileActivityInterface.onGetMyProfileFailure(t.message ?: "통신 오류")
            }
        })
    }
}