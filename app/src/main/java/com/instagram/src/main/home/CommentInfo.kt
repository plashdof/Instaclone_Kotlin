package com.instagram.src.main.home

object CommentInfo {
    private var postId : Int? = null

    fun getpostId() : Int?{
        return postId
    }
    fun setpostId(data : Int?){
        postId = data
    }
}