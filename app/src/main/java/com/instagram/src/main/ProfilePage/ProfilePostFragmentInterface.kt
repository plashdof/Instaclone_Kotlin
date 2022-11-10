package com.instagram.src.main.ProfilePage

import com.instagram.src.main.ProfilePage.models.UserPostListData
import com.instagram.src.main.ProfilePage.models.UserPostThumbnailData

interface ProfilePostFragmentInterface {

    fun onGetUserPostThumbnailSuccesss(response : UserPostThumbnailData)
    fun onGetUserPostThumbnailFailure(message : String)

    fun onGetUserPostListSuccess(response: UserPostListData)
    fun onGetUserPostListFailure(message : String)

    fun onGetUserTaggedThumbnailSuccess(response : UserPostThumbnailData)
    fun onGetUserTaggedThumbnailFailure(message : String)

    fun onGetUserTaggedListSuccess(response:UserPostListData)
    fun onGetUserTaggedListFailure(message : String)

}