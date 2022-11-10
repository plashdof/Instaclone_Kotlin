package com.instagram.src.main.ProfilePage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentProfilePostBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.ProfilePage.adapter.ProfilePostAdapter
import com.instagram.src.main.ProfilePage.models.UserPostListData
import com.instagram.src.main.ProfilePage.models.UserPostThumbnailData
import com.instagram.src.main.SignupPages.models.userId
import com.instagram.src.main.home.StoryToolActivity
import com.instagram.src.main.home.adapter.PostAdapter
import com.instagram.src.main.home.models.PostdetialData

class ProfilePostFragment : BaseFragment<FragmentProfilePostBinding>(FragmentProfilePostBinding::bind, R.layout.fragment_profile_post),ProfilePostFragmentInterface{

    val poststate = OtherProfileInfo.getpoststate()
    val postId = OtherProfileInfo.getpostId()
    val userId = OtherProfileInfo.getId()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        // ProfileFragment 에서 썸네일 클릭시,  태그된게시물인지  내게시물인지 구분!
        
        setFragmentResultListener("fromProfileFragment"){requestKey, bundle ->
            val result = bundle.getBoolean("bundleKey")

            if(result) {
                binding.tvProfilepostHeader.text = "게시물"

            } else{
                binding.tvProfilepostHeader.text = "태그됨"
            }
        }

        if(poststate != null && postId != null && userId != null){

            if(poststate == 0){
                ProfilePostService(this).tryGetUserPostList(Jwt.getjwt(),postId,0)  // 클릭한 썸네일이 내 게시물일 경우
            }else if(poststate == 1){
                ProfilePostService(this).tryGetUserTaggedList(Jwt.getjwt(),userId,postId,0)  //  클릭한 썸네일이 태그된 게시물일 경우
            }

        }

        // 뒤로가기
        
        binding.btnProfilepostBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("ProfileOthers")
        }

    }

    override fun onGetUserPostListSuccess(response: UserPostListData) {
        recyclerPost(response.result.postList)
    }

    override fun onGetUserPostListFailure(message: String) {}

    override fun onGetUserTaggedListSuccess(response: UserPostListData) {
        recyclerPost(response.result.postList)
    }

    override fun onGetUserTaggedListFailure(message: String) {}


    override fun onGetUserPostThumbnailSuccesss(response: UserPostThumbnailData) {}
    override fun onGetUserPostThumbnailFailure(message: String) {}
    override fun onGetUserTaggedThumbnailSuccess(response: UserPostThumbnailData) {}
    override fun onGetUserTaggedThumbnailFailure(message: String) {}


    private fun recyclerPost(datas : ArrayList<PostdetialData>){

        val linking = getcontext()
        val adapter = ProfilePostAdapter(datas, linking)
        binding.recyclerProfilepost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerProfilepost.adapter = adapter
    }


    inner class getcontext{
        val fragcontext = context
        fun gotoComment(postid : Int){
            movetoCommentPage(postid)
        }
        fun gotoLikelist(postid : Int){
            movetoLikelistPage(postid)
        }

        fun gotoOthersprofile(targetNickname: String?, userId:Int){
            movetoOthersprofilePage(targetNickname, userId)
        }

        fun gotoStoryTool(targetNickname: String?){
            movetoStorytoolPage(targetNickname)
        }
    }


    fun movetoCommentPage(postid : Int){
        val Activity = activity as MainActivity
        setFragmentResult("fromHome", bundleOf("bundleKey" to postid))
        Activity.changeFragment("Comment")
    }

    fun movetoLikelistPage(postid : Int){
        val Activity = activity as MainActivity
        setFragmentResult("fromHome", bundleOf("bundleKey" to postid))
        Activity.changeFragment("Likelist")
    }

    fun movetoOthersprofilePage(targetNickname : String?, userId: Int){
        val data = arrayOf(targetNickname, userId.toString())
        val Activity = activity as MainActivity
        setFragmentResult("fromHome", bundleOf("bundleKey" to data))
        Activity.changeFragment("ProfileOthers")
    }



    fun movetoStorytoolPage(targetNickname: String?){

        Log.d("aaaaa", "$targetNickname")
        val intent = Intent(context,StoryToolActivity::class.java)
            .putExtra("currentNick", targetNickname)
        startActivity(intent)



    }

}