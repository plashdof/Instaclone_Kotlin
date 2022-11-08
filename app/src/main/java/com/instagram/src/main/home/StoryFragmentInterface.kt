package com.instagram.src.main.home

import com.instagram.src.main.home.models.MakeStoryResponseData
import com.instagram.src.main.home.models.UserStoryData

interface StoryFragmentInterface {

    fun onPostMakeStorySuccess(response: MakeStoryResponseData)
    fun onPostMakeStoryFailure(message:String)

    fun onGetUserStorySuccess(response: UserStoryData)
    fun onGetUserStoryFailure(message:String)
}