package com.instagram.src.main.Login

import com.instagram.src.main.Login.models.LoginData

interface LoginActivityInterface {
    fun onPostLoginSuccess(response: LoginData)

    fun onPostLoginFailure(message: String)

}