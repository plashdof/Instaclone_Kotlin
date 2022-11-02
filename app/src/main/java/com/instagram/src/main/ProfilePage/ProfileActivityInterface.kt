package com.instagram.src.main.ProfilePage

import com.instagram.src.main.ProfilePage.models.MyProfileData

interface ProfileActivityInterface {

    fun onGetMyProfileSuccess(response: MyProfileData)

    fun onGetMyProfileFailure(message: String)
}