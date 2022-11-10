package com.instagram.src.main.ProfilePage

import com.instagram.config.ApplicationClass
import com.instagram.src.main.ProfilePage.models.UserPostListData
import com.instagram.src.main.ProfilePage.models.UserPostThumbnailData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePostService(val profilePostFragmentInterface: ProfilePostFragmentInterface) {

    fun tryGetUserPostThumbnail(jwt: String?, userId: Int, page: Int){
        val profilePostRetrofitInterface = ApplicationClass.sRetrofit.create(ProfilePostRetrofitInterface::class.java)
        profilePostRetrofitInterface.getUserPostThumbnail(jwt, userId, page).enqueue(object: Callback<UserPostThumbnailData> {
            override fun onResponse(call: Call<UserPostThumbnailData>, response: Response<UserPostThumbnailData>) {
                profilePostFragmentInterface.onGetUserPostThumbnailSuccesss(response.body() as UserPostThumbnailData)
            }

            override fun onFailure(call: Call<UserPostThumbnailData>, t: Throwable) {
                profilePostFragmentInterface.onGetUserPostThumbnailFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetUserPostList(jwt : String?, postId : Int, page : Int){
        val profilePostRetrofitInterface = ApplicationClass.sRetrofit.create(ProfilePostRetrofitInterface::class.java)
        profilePostRetrofitInterface.getUserPostList(jwt, postId, page).enqueue(object: Callback<UserPostListData> {
            override fun onResponse(call: Call<UserPostListData>, response: Response<UserPostListData>) {
                profilePostFragmentInterface.onGetUserPostListSuccess(response.body() as UserPostListData)
            }

            override fun onFailure(call: Call<UserPostListData>, t: Throwable) {
                profilePostFragmentInterface.onGetUserPostListFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetUserTaggedThumbnail(jwt: String?, userId: Int, page: Int){
        val profilePostRetrofitInterface = ApplicationClass.sRetrofit.create(ProfilePostRetrofitInterface::class.java)
        profilePostRetrofitInterface.getUserTaggedThumbnail(jwt, userId, page).enqueue(object: Callback<UserPostThumbnailData> {
            override fun onResponse(call: Call<UserPostThumbnailData>, response: Response<UserPostThumbnailData>) {
                profilePostFragmentInterface.onGetUserTaggedThumbnailSuccess(response.body() as UserPostThumbnailData)
            }

            override fun onFailure(call: Call<UserPostThumbnailData>, t: Throwable) {
                profilePostFragmentInterface.onGetUserTaggedThumbnailFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetUserTaggedList(jwt : String?, userId : Int,postId : Int, page : Int){
        val profilePostRetrofitInterface = ApplicationClass.sRetrofit.create(ProfilePostRetrofitInterface::class.java)
        profilePostRetrofitInterface.getUserTaggedList(jwt, userId,postId,page).enqueue(object: Callback<UserPostListData> {
            override fun onResponse(call: Call<UserPostListData>, response: Response<UserPostListData>) {
                profilePostFragmentInterface.onGetUserTaggedListSuccess(response.body() as UserPostListData)
            }

            override fun onFailure(call: Call<UserPostListData>, t: Throwable) {
                profilePostFragmentInterface.onGetUserTaggedListFailure(t.message ?: "통신 오류")
            }
        })
    }



}