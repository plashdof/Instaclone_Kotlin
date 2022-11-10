package com.instagram.src.main.home

import com.instagram.src.main.home.models.CommentContentData
import com.instagram.src.main.home.models.CommentData
import com.instagram.src.main.home.models.PostlikeData

interface CommentFragmentInterface {

    fun onGetCommentDataSuccess(response: CommentData)
    fun onGetCommentDataFailure(message : String)

    fun onGetCommentContentDataSuccess(response  : CommentContentData)
    fun onGetCommentContentDataFailure(message : String)

    fun onPostCommentSuccess(response: PostlikeData)
    fun onPostCommentFailure(message : String)

}