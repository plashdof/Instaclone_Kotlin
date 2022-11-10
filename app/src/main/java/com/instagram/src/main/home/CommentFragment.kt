package com.instagram.src.main.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
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
import com.instagram.src.main.home.API.CommentAPI
import com.instagram.src.main.home.adapter.CommentAdapter
import com.instagram.src.main.home.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommentFragment : BaseFragment<FragmentCommentBinding>(FragmentCommentBinding::bind, R.layout.fragment_comment),CommentFragmentInterface {

    var page = 0
    var addcommenttext = ""
    var postId = 0
    var parentId = 0
    var commentflag = 0


    inner class getcontext{
        val fragcontext = context

        fun gotoLiketoOthersprofile(targetNickname: String?, userId:Int){
            moveLiketoOthersprofilePage(targetNickname, userId)
        }

        fun refreshcomment(){
            val Activity = activity as MainActivity
            Activity.changeFragment("Comment")
        }

        fun startCocoment(data: Int){
            parentId = data
            commentflag = 1

            //  edittext 에 포커싱
            binding.etPostcomment.requestFocus()

            // 키보드 올리기
            val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(binding.etPostcomment,0)

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

            if(commentflag == 0){   // 댓글 추가하기
                val data = AddCommentData(postId = postId, content = addcommenttext)
                CommentService(this).tryPostComment(Jwt.getjwt(),data)
            }else{  // 대댓글추가하기

                val data = AddCocommentData(postId=postId,content=addcommenttext,parentId=parentId)

                val buildLikeRetro = Retrofit.Builder()
                    .baseUrl("https://prod.lukechoi.shop/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val api = buildLikeRetro.create(CommentAPI::class.java)
                api.postCocomments(Jwt.getjwt(), params = data)
                    .enqueue(object: Callback<PostlikeData> {
                        override fun onResponse(
                            call: Call<PostlikeData>,
                            response: Response<PostlikeData>
                        ) {
                            Log.d("API결과","${response.body().toString()}")
                            Log.d("API결과","${response.raw()}")

                            if(response.body()?.isSuccess == true){
                                val Activity = activity as MainActivity
                                Activity.changeFragment("Comment")
                            }


                        }
                        override fun onFailure(call: Call<PostlikeData>, t: Throwable) {
                            Log.d("API결과", "실패 : $t")
                        }
                    })
            }

        }

    }

    override fun onGetCommentDataSuccess(response: CommentData) {
        if(response.isSuccess){
//            val data =response.result.commentList
//            val result = arrayListOf<CommentdetailData>()
//            var count = 0
//            for(i in 0 until data.size){
//                count = data[i].commentNum
//                if(count >= 0){
//
//                    result.add(data[i])
//                    Log.d("dddddd","부모댓글추가됨 : ${result.toString()}")
//                }
//                if(count > 1){
//                    for(j in i until data.size){
//                        if(data[j].commentNum == -1 && data[j].parentId == data[i].commentId){
//                            result.add(data[j])
//                            Log.d("dddddd","대댓글추가됨 : ${result.toString()}")
//                        }
//                    }
//                }
//            }
//
//            Log.d("dddddd","${result.toString()}")


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