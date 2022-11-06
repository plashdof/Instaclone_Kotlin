package com.instagram.src.main.home

import com.instagram.src.main.home.models.PostData

interface HomeFragmentInterface {


    fun onGetHomePostDataSuccess(response:PostData)

    fun onGetHomePostDataFailure(message:String)
}