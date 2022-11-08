package com.instagram.src.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentCommentBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.home.adapter.CommentAdapter
import com.instagram.src.main.home.models.CommentContentData
import com.instagram.src.main.home.models.CommentData
import com.instagram.src.main.home.models.CommentdetailData

class CommentFragment : BaseFragment<FragmentCommentBinding>(FragmentCommentBinding::bind, R.layout.fragment_comment),CommentFragmentInterface {

    var page = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setFragmentResultListener("fromHome"){requestKey, bundle ->
            val postId = bundle.getInt("bundleKey")
            CommentService(this).tryGetCommentData(Jwt.getjwt(),postId,page)
            CommentService(this).tryGetCommentContentData(Jwt.getjwt(),postId)
        }

        binding.btnCommentBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("Home")
        }

    }

    override fun onGetCommentDataSuccess(response: CommentData) {
        recyclerComment(response.result.commentList)
    }

    override fun onGetCommentDataFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetCommentContentDataSuccess(response: CommentContentData) {
        binding.commentOwnernick.text = response.result.nickname
        binding.commentOwnercontent.text = response.result.content
        binding.commentOwnertime.text = response.result.time
        Glide.with(this)
            .load(response.result.userImg)
            .into(binding.commentOwnerimage)
    }

    override fun onGetCommentContentDataFailure(message: String) {
        TODO("Not yet implemented")
    }

    fun recyclerComment(datas : ArrayList<CommentdetailData>){

        val adapter = CommentAdapter(datas)
        binding.recyclerComment.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerComment.adapter = adapter

    }
}