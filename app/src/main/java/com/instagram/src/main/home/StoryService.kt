package com.instagram.src.main.home

import com.instagram.config.ApplicationClass
import com.instagram.src.main.home.models.MakeStoryResponseData
import com.instagram.src.main.home.models.PostMakeStoryData
import com.instagram.src.main.home.models.UserStoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryService(val storyFragmentInterface: StoryFragmentInterface) {


    fun tryPostMakeStoryData(jwt:String?, params: PostMakeStoryData){
        val storyRetrofitInterface = ApplicationClass.sRetrofit.create(StoryRetrofitInterface::class.java)
        storyRetrofitInterface.postMakeStory(jwt, params).enqueue(object:
            Callback<MakeStoryResponseData> {
            override fun onResponse(
                call: Call<MakeStoryResponseData>,
                response: Response<MakeStoryResponseData>
            ) {
                storyFragmentInterface.onPostMakeStorySuccess(response.body() as MakeStoryResponseData)
            }

            override fun onFailure(call: Call<MakeStoryResponseData>, t: Throwable) {
                storyFragmentInterface.onPostMakeStoryFailure(t.message ?:"통신오류")
            }
        })
    }

    fun tryGetUserStoryData(jwt:String?, targetUser:String?){
        val storyRetrofitInterface = ApplicationClass.sRetrofit.create(StoryRetrofitInterface::class.java)
        storyRetrofitInterface.getUserStory(jwt, targetUser).enqueue(object: Callback<UserStoryData>{
            override fun onResponse(call: Call<UserStoryData>, response: Response<UserStoryData>) {
                storyFragmentInterface.onGetUserStorySuccess(response.body() as UserStoryData)
            }

            override fun onFailure(call: Call<UserStoryData>, t: Throwable) {
                storyFragmentInterface.onGetUserStoryFailure(t.message?:"통신오류")
           }
        })
    }
}