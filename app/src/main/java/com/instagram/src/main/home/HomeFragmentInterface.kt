package com.instagram.src.main.home

import com.instagram.src.main.home.models.SignUpResponse
import com.instagram.src.main.home.models.UserResponse

interface HomeFragmentInterface {

    fun onGetUserSuccess(response: UserResponse)

    fun onGetUserFailure(message: String)

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)
}