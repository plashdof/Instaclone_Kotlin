package com.instagram.src.main

object Jwt {
    private var jwt : String? = ""

    fun setjwt(data : String?){
        jwt = data
    }

    fun getjwt() : String?{
        return jwt
    }
}