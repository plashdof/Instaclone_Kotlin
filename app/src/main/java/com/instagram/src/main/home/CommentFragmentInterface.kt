package com.instagram.src.main.home

import com.instagram.src.main.home.models.CommentContentData
import com.instagram.src.main.home.models.CommentData

interface CommentFragmentInterface {

    fun onGetCommentDataSuccess(response: CommentData)

    fun onGetCommentDataFailure(message : String)

    fun onGetCommentContentDataSuccess(response  : CommentContentData)

    fun onGetCommentContentDataFailure(message : String)
}