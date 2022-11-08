package com.instagram.src.main

object MyInfo {
    private var profileimg : String? = ""
    private var userId : Int? = 0
    private var nickname : String? = ""

    fun setnickname(data : String?){
        nickname = data
    }

    fun getnickname() : String?{
        return nickname
    }

    fun setuserId(data : Int?){
        userId = data
    }

    fun getuserId() : Int?{
        return userId
    }

    fun setprofileimg(data : String?){
        profileimg = data
    }

    fun getprofileimg() : String?{
        return profileimg
    }
}