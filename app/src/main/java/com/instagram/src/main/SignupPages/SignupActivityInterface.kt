package com.instagram.src.main.SignupPages

import com.instagram.src.main.SignupPages.models.CheckuserKey
import com.instagram.src.main.SignupPages.models.CheckuserNick
import com.instagram.src.main.SignupPages.models.Signup

interface SignupActivityInterface {

    fun onGetCheckuserKeySuccess(response: CheckuserKey)

    fun onGetCheckuserKeyFailure(message: String)

    fun onGetCheckuserNickSuccess(response: CheckuserNick)

    fun onGetCheckuserNickFailure(message: String)

    fun onPostSignupSuccess(response: Signup)

    fun onPostSignupFailure(message: String)

}