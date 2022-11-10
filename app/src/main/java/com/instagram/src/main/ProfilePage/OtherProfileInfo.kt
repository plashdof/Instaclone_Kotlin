package com.instagram.src.main.ProfilePage

object OtherProfileInfo {
    private var userId : Int? = 0
    private var usernick : String? = ""

    private var postId : Int? = 0
    private var poststate : Int? = 0

    fun setId(data : Int?){
        userId = data
    }

    fun getId() : Int?{
        return userId
    }

    fun setnick(data : String?){
        usernick = data
    }

    fun getnick(): String?{
        return usernick
    }

    fun setpostId(data :Int?){
        postId = data
    }

    fun getpostId() : Int?{
        return postId
    }

    fun setpoststate(data : Int?){
        poststate = data
    }

    fun getpoststate() : Int?{
        return poststate
    }

}