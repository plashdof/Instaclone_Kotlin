package com.instagram.src.main.home

import android.content.Context
import android.util.Log
import com.instagram.config.ApplicationClass
import com.instagram.src.main.home.models.PostData
import com.instagram.src.main.home.models.PostlikeData
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


}