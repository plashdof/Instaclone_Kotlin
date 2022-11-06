package com.instagram.src.main.home

import com.instagram.src.main.home.models.LikelistData
import com.instagram.src.main.home.models.PostData
import com.instagram.src.main.home.models.PostlikeData

interface HomeFragmentInterface {


    fun onGetHomePostDataSuccess(response:PostData)

    fun onGetHomePostDataFailure(message:String)

    fun onGetLikelistSuccess(response:LikelistData)

    fun onGetLikelistFailure(message:String)

}