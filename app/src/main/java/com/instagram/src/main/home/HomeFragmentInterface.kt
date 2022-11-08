package com.instagram.src.main.home

import com.instagram.src.main.home.models.*

interface HomeFragmentInterface {


    fun onGetHomePostDataSuccess(response:PostData)

    fun onGetHomePostDataFailure(message:String)

    fun onGetLikelistSuccess(response:LikelistData)

    fun onGetLikelistFailure(message:String)

    fun onGetStoryDataSuccess(response: StoryData)

    fun onGetStoryDataFailure(message:String)



}