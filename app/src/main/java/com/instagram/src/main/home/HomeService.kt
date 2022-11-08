package com.instagram.src.main.home

import android.content.Context
import android.util.Log
import com.instagram.config.ApplicationClass
import com.instagram.src.main.home.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val homeFragmentInterface: HomeFragmentInterface) {
    
    fun tryGetHomePostData(jwt:String?, page:String?){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getHomepostView(jwt,page).enqueue(object: Callback<PostData> {
            override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                homeFragmentInterface.onGetHomePostDataSuccess(response.body() as PostData)
            }

            override fun onFailure(call: Call<PostData>, t: Throwable) {
                homeFragmentInterface.onGetHomePostDataFailure(t.message ?:"통신오류")
            }
            
            
        })
    }

    fun tryGetLikelist(jwt:String?, postid:String?){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getLikelist(jwt,postid).enqueue(object: Callback<LikelistData> {
            override fun onResponse(call: Call<LikelistData>, response: Response<LikelistData>) {
                homeFragmentInterface.onGetLikelistSuccess(response.body() as LikelistData)
            }

            override fun onFailure(call: Call<LikelistData>, t: Throwable) {
                homeFragmentInterface.onGetLikelistFailure(t.message ?:"통신오류")
            }


        })
    }

    fun tryGetStoryData(jwt:String?){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getStory(jwt).enqueue(object:Callback<StoryData>{
            override fun onResponse(call: Call<StoryData>, response: Response<StoryData>) {
                homeFragmentInterface.onGetStoryDataSuccess(response.body() as StoryData)
            }

            override fun onFailure(call: Call<StoryData>, t: Throwable) {
                homeFragmentInterface.onGetStoryDataFailure(t.message ?:"통신오류")
            }
        })
    }




}