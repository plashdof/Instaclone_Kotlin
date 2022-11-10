package com.instagram.src.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentCommentBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.MyInfo
import com.instagram.src.main.home.adapter.CommentAdapter
import com.instagram.src.main.home.models.*

class CommentFragment : BaseFragment<FragmentCommentBinding>(FragmentCommentBinding::bind, R.layout.fragment_comment),CommentFragmentInterface {

    var page = 0
    var addcommenttext = ""
    var postId = 0

    inner class getcontext{
        val fragcontext = context

        fun gotoLiketoOthersprofile(targetNickname: String?, userId:Int){
            moveLiketoOthersprofilePage(targetNickname, userId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 초기 댓글달기 정보 세팅
        binding.etPostcomment.hint = "${MyInfo.getnickname()}으로 댓글달기"
        Glide.with(this)
            .load(MyInfo.getprofileimg())
            .into(binding.ivCommentProfile)



        setFragmentResultListener("fromHome"){requestKey, bundle ->
            postId = bundle.getInt("bundleKey")
            CommentInfo.setpostId(postId)
            CommentService(this).tryGetCommentData(Jwt.getjwt(),postId,page)
            CommentService(this).tryGetCommentContentData(Jwt.getjwt(),postId)
        }

        if(CommentInfo.getpostId() != null){
            CommentInfo.getpostId()
                ?.let { CommentService(this).tryGetCommentData(Jwt.getjwt(), it,page) }
            CommentInfo.getpostId()
                ?.let { CommentService(this).tryGetCommentContentData(Jwt.getjwt(), it) }
        }

        binding.btnCommentBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("Home")
        }

        // et 에 텍스트 입력시 버튼 활성화
        binding.etPostcomment.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                addcommenttext = binding.etPostcomment.text.toString()
                
                if(addcommenttext.isNotBlank()){
                    binding.btnCommentMakecommentbtn.setTextColor(R.color.insta_login_textbtn)
                    binding.btnCommentMakecommentbtn.isClickable = true
                }else{
                    binding.btnCommentMakecommentbtn.setTextColor(R.color.insta_login_btn)
                    binding.btnCommentMakecommentbtn.isClickable = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })


        // 댓글 추가하기
        binding.btnCommentMakecommentbtn.setOnClickListener {
            val data = AddCommentData(postId = postId, content = addcommenttext)
            CommentService(this).tryPostComment(Jwt.getjwt(),data)
        }

    }

    override fun onGetCommentDataSuccess(response: CommentData) {
        if(response.isSuccess){
            recyclerComment(response.result.commentList)
        }
    }

    override fun onGetCommentDataFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetCommentContentDataSuccess(response: CommentContentData) {
        if(response.isSuccess){
            binding.commentOwnernick.text = response.result.nickname
            binding.commentOwnercontent.text = response.result.content
            binding.commentOwnertime.text = response.result.time
            Glide.with(this)
                .load(response.result.userImg)
                .into(binding.commentOwnerimage)
        }

    }

    override fun onGetCommentContentDataFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPostCommentSuccess(response: PostlikeData) {
        if(response.isSuccess){
            val Activity = activity as MainActivity
            Activity.changeFragment("Comment")
        }
    }

    override fun onPostCommentFailure(message: String) {
        TODO("Not yet implemented")
    }

    fun recyclerComment(datas : ArrayList<CommentdetailData>){

        val linking = getcontext()
        val adapter = CommentAdapter(datas, linking)
        binding.recyclerComment.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerComment.adapter = adapter

    }

    fun moveLiketoOthersprofilePage(targetNickname : String?, userId: Int){

        Log.d("aaaaa","moveLiketoOthers $targetNickname   $userId")

        val data = arrayOf(targetNickname, userId.toString())
        val Activity = activity as MainActivity
        setFragmentResult("fromLikelist", bundleOf("bundleKey" to data))
        Activity.changeFragment("ProfileOthers")
    }
}