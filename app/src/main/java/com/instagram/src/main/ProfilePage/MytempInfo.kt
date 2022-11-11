package com.instagram.src.main.ProfilePage

object MytempInfo {
    private var profileimg : String? = ""
    private var userId : Int = 0
    private var nickname : String? = ""
    private var website : String? = ""
    private var name : String? = ""
    private var description : String? =""

    fun setwebsite(data : String?){
        website = data
    }
    fun getwebsite():String?{
        return website
    }

    fun setname(data : String?){
        name = data
    }
    fun getname():String?{
        return name
    }
    fun setdescription(data : String?){
        description = data
    }
    fun getdescription():String?{
        return description
    }

    fun setnickname(data : String?){
        nickname = data
    }

    fun getnickname() : String?{
        return nickname
    }

    fun setuserId(data : Int){
        userId = data
    }

    fun getuserId() : Int{
        return userId
    }

    fun setprofileimg(data : String?){
        profileimg = data
    }

    fun getprofileimg() : String?{
        return profileimg
    }
}