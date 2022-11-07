package com.instagram.src.main.ProfilePage

import com.instagram.config.ApplicationClass
import com.instagram.src.main.ProfilePage.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileService(val profileFragmentInterface: ProfileFragmentInterface) {

    fun tryGetMyProfileData(jwt : String?){
        val profileRetrofitInterface = ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
        profileRetrofitInterface.getMyProfile(jwt).enqueue(object: Callback<MyProfileData>{
            override fun onResponse(call: Call<MyProfileData>, response: Response<MyProfileData>) {
                profileFragmentInterface.onGetMyProfileSuccess(response.body() as MyProfileData)
            }

            override fun onFailure(call: Call<MyProfileData>, t: Throwable) {
                profileFragmentInterface.onGetMyProfileFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetOthersProfileData(jwt : String?, targetNickname:String?){
        val profileRetrofitInterface = ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
        profileRetrofitInterface.getOthersProfile(jwt, targetNickname).enqueue(object: Callback<OthersProfileData>{
            override fun onResponse(call: Call<OthersProfileData>, response: Response<OthersProfileData>
            ) {
                profileFragmentInterface.onGetOthersProfileSuccess(response.body() as OthersProfileData)
            }

            override fun onFailure(call: Call<OthersProfileData>, t: Throwable) {
                profileFragmentInterface.onGetOthersProfileFailure(t.message?: "통신 오류")
            }
        })
    }

    fun tryPatchProfileModifyData(jwt : String?, datas:ModifyProfileBodyData){
        val profileRetrofitInterface = ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
        profileRetrofitInterface.patchMyProfile(jwt, datas).enqueue(object: Callback<ModifyProfileData>{
            override fun onResponse(call: Call<ModifyProfileData>, response: Response<ModifyProfileData>
            ) {
                profileFragmentInterface.onPatchModifyProfileSuccess(response.body() as ModifyProfileData)
            }

            override fun onFailure(call: Call<ModifyProfileData>, t: Throwable) {
                profileFragmentInterface.onPatchModifyProfileFailure(t.message?: "통신 오류")
            }
        })
    }

    fun tryPostFollowingData(jwt:String?, targetId:Int){
        val profileRetrofitInterface = ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
        profileRetrofitInterface.postFollows(jwt,targetId).enqueue(object: Callback<PostFollowingData>{
            override fun onResponse(
                call: Call<PostFollowingData>,
                response: Response<PostFollowingData>
            ) {
                profileFragmentInterface.onPostFollowingSuccess(response.body() as PostFollowingData)
            }

            override fun onFailure(call: Call<PostFollowingData>, t: Throwable) {
                profileFragmentInterface.onPostFollowingFailure(t.message?: "통신 오류")
            }
        })
    }

    fun tryPatchunFollowingData(jwt:String?, targetId:Int){
        val profileRetrofitInterface = ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
        profileRetrofitInterface.patchunFollow(jwt,targetId).enqueue(object: Callback<PostFollowingData>{
            override fun onResponse(
                call: Call<PostFollowingData>,
                response: Response<PostFollowingData>
            ) {
                profileFragmentInterface.onPatchunFollowingSuccess(response.body() as PostFollowingData)
            }

            override fun onFailure(call: Call<PostFollowingData>, t: Throwable) {
                profileFragmentInterface.onPatchunFollowingFailure(t.message?: "통신 오류")
            }
        })
    }
}