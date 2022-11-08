package com.instagram.src.main.home

import com.instagram.config.ApplicationClass
import com.instagram.src.main.home.models.CommentContentData
import com.instagram.src.main.home.models.CommentData
import com.instagram.src.main.home.models.PostData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentService(val commentFragmentInterface: CommentFragmentInterface) {


    fun tryGetCommentData(jwt:String?, postId:Int, page:Int){
        val commentRetrofitInterface = ApplicationClass.sRetrofit.create(CommentRetrofitInterface::class.java)
        commentRetrofitInterface.getComments(jwt,postId,page).enqueue(object: Callback<CommentData>{
            override fun onResponse(call: Call<CommentData>, response: Response<CommentData>) {
                commentFragmentInterface.onGetCommentDataSuccess(response.body() as CommentData)
            }

            override fun onFailure(call: Call<CommentData>, t: Throwable) {
                commentFragmentInterface.onGetCommentDataFailure(t.message ?:"통신오류")
            }
        })
    }

    fun tryGetCommentContentData(jwt:String?, postId:Int){
        val commentRetrofitInterface = ApplicationClass.sRetrofit.create(CommentRetrofitInterface::class.java)
        commentRetrofitInterface.getCommentContent(jwt,postId).enqueue(object: Callback<CommentContentData>{
            override fun onResponse(call: Call<CommentContentData>, response: Response<CommentContentData>) {
                commentFragmentInterface.onGetCommentContentDataSuccess(response.body() as CommentContentData)
            }

            override fun onFailure(call: Call<CommentContentData>, t: Throwable) {
                commentFragmentInterface.onGetCommentContentDataFailure(t.message ?:"통신오류")
            }
        })
    }
}