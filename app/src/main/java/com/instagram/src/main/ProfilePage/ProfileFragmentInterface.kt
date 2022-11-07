package com.instagram.src.main.ProfilePage

import com.instagram.src.main.ProfilePage.models.ModifyProfileData
import com.instagram.src.main.ProfilePage.models.MyProfileData
import com.instagram.src.main.ProfilePage.models.OthersProfileData
import com.instagram.src.main.ProfilePage.models.PostFollowingData

interface ProfileFragmentInterface {

    fun onGetMyProfileSuccess(response: MyProfileData)
    fun onGetMyProfileFailure(message: String)

    fun onGetOthersProfileSuccess(response: OthersProfileData)
    fun onGetOthersProfileFailure(message: String)

    fun onPatchModifyProfileSuccess(response: ModifyProfileData)
    fun onPatchModifyProfileFailure(message :String)

    fun onPostFollowingSuccess(response:PostFollowingData)
    fun onPostFollowingFailure(message:String)

    fun onPatchunFollowingSuccess(response:PostFollowingData)

    fun onPatchunFollowingFailure(message:String)
}