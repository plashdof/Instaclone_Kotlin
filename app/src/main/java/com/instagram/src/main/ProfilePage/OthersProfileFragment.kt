package com.instagram.src.main.ProfilePage

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentProfileOthersBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.MyInfo
import com.instagram.src.main.ProfilePage.adapter.OthersProfileThumbnailAdapter
import com.instagram.src.main.ProfilePage.models.*

class OthersProfileFragment : BaseFragment<FragmentProfileOthersBinding>(FragmentProfileOthersBinding::bind, R.layout.fragment_profile_others),ProfileFragmentInterface, ProfilePostFragmentInterface {


    var userId : Int=0
    var poststate : Int = 0

    inner class roomToAdapter{
        fun moveToProfilePost(postId : Int){
            changeProfile(postId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("fromHome"){requestKey, bundle ->
            val result = bundle.getStringArray("bundleKey")
            val targetNick = result?.get(0)
            userId = result?.get(1)?.toInt()!!
            OtherProfileInfo.setId(userId)
            OtherProfileInfo.setnick(targetNick)
            Log.d("ddddd","$userId")
            ProfileService(this).tryGetOthersProfileData(Jwt.getjwt(), targetNick)
        }

        setFragmentResultListener("fromLikelist"){requestKey, bundle ->
            val result = bundle.getStringArray("bundleKey")
            Log.d("aaaaa", "타인프로필 $result")
            val targetNick = result?.get(0)
            userId = result?.get(1)?.toInt()!!
            OtherProfileInfo.setId(userId)
            OtherProfileInfo.setnick(targetNick)
            ProfileService(this).tryGetOthersProfileData(Jwt.getjwt(), targetNick)
            }

        if(OtherProfileInfo.getnick()?.isNotBlank() == true){
            ProfileService(this).tryGetOthersProfileData(Jwt.getjwt(), OtherProfileInfo.getnick())
        }


        binding.btnOthersprofileBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("Home")
        }

        binding.btnOthersprofileFollowbtn.setOnClickListener {
            if(binding.btnOthersprofileFollowbtn.text == "팔로우"){
                userId?.let { it1 ->
                    ProfileService(this).tryPostFollowingData(Jwt.getjwt(),
                        it1.toInt())              
                }

            }else{
                userId?.let { it1 ->
                    ProfileService(this).tryPatchunFollowingData(Jwt.getjwt(),
                        it1.toInt())
                }
            }
        }


        // 게시물버튼 클릭

        binding.btnOthersprofileMypost.setOnClickListener {
            binding.btnOthersprofileMypost.setBackgroundResource(R.drawable.shape_signupbtn_active)
            binding.btnOthersprofileMypost.alpha = 1F

            binding.btnOthersprofileTagpost.setBackgroundResource(R.drawable.shape_signupbtn)
            binding.btnOthersprofileTagpost.alpha = 0.3F


            OtherProfileInfo.getId()?.let { it1 ->
                ProfilePostService(this).tryGetUserPostThumbnail(Jwt.getjwt(),
                    it1,0)
            }
            poststate = 0
        }

        // 태그된목록 버튼 클릭

        binding.btnOthersprofileTagpost.setOnClickListener {

            binding.btnOthersprofileTagpost.setBackgroundResource(R.drawable.shape_signupbtn_active)
            binding.btnOthersprofileTagpost.alpha = 1F

            binding.btnOthersprofileMypost.setBackgroundResource(R.drawable.shape_signupbtn)
            binding.btnOthersprofileMypost.alpha = 0.3F


            OtherProfileInfo.getId()?.let { it1 ->
                ProfilePostService(this).tryGetUserTaggedThumbnail(Jwt.getjwt(),
                    it1,0)
            }
            poststate = 1
        }

    }

    override fun onGetOthersProfileSuccess(response: OthersProfileData) {

        val togetherlist = response.result.followTogetherList
        val myfollowing = response.result.followingStatus

        if(response.isSuccess){
            Glide.with(this)
                .load(response.result.profileUrl)
                .into(binding.btnOthersprofileImage)


            binding.tvOthersprofileDescription.text = response.result.description
            binding.tvOthersprofileWebsite.text = response.result.link
            binding.tvOthersprofileRealname.text = response.result.name
            binding.tvOthersprofileNickname.text= response.result.nickname
            binding.tvOthersprofileFollowingCount.text = response.result.followingCount.toString()
            binding.tvOthersprofileFollowerCount.text = response.result.followerCount.toString()
            binding.tvOthersprofilePostcount.text = response.result.postCount.toString()

            // 나의 팔로우 팔로잉 여부 체크

            if(myfollowing == "Y"){
                binding.btnOthersprofileFollowbtn.setBackgroundResource(R.drawable.shape_et)
                binding.btnOthersprofileFollowbtn.text = "팔로잉"
                binding.btnOthersprofileFollowbtn.setTextColor(Color.BLACK)
            }else{
                binding.btnOthersprofileFollowbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
                binding.btnOthersprofileFollowbtn.text = "팔로우"
                binding.btnOthersprofileFollowbtn.setTextColor(Color.WHITE)
            }

            // 함꼐 팔로우하는 목록

            if(!togetherlist.isEmpty()){
                binding.layoutOthersprofileFollowtogether.isVisible = true
                Glide.with(this)
                    .load(response.result.followTogetherList[0].profileUrl)
                    .into(binding.followtogetherImg)
                binding.followtogetherCount.text = "${(response.result.followTogetherList.size - 1)}명"
                binding.followtogetherNick.text = response.result.followTogetherList[0].nickname
            }else{
                binding.layoutOthersprofileFollowtogether.isVisible = false
            }

            OtherProfileInfo.getId()
                ?.let { ProfilePostService(this).tryGetUserPostThumbnail(Jwt.getjwt(), it,0) }



        }

    }

    override fun onPostFollowingSuccess(response: PostFollowingData) {
        if(response.isSuccess){
            binding.btnOthersprofileFollowbtn.setBackgroundResource(R.drawable.shape_et)
            binding.btnOthersprofileFollowbtn.text = "팔로잉"
            binding.btnOthersprofileFollowbtn.setTextColor(Color.BLACK)
        }
    }
    override fun onPostFollowingFailure(message: String) {
    }

    override fun onPatchunFollowingSuccess(response: PostFollowingData) {
        binding.btnOthersprofileFollowbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
        binding.btnOthersprofileFollowbtn.text = "팔로우"
        binding.btnOthersprofileFollowbtn.setTextColor(Color.WHITE)
    }
    override fun onPatchunFollowingFailure(message: String) {}

    override fun onGetOthersProfileFailure(message: String) {}
    override fun onGetMyProfileSuccess(response: MyProfileData) {}
    override fun onGetMyProfileFailure(message: String) {}
    override fun onPatchModifyProfileSuccess(response: ModifyProfileData) {}
    override fun onPatchModifyProfileFailure(message: String) {}


    override fun onGetUserPostThumbnailSuccesss(response: UserPostThumbnailData) {
        if(response.isSuccess){
            recyclerMypost(response.result.thumbnailList)
        }
    }

    override fun onGetUserPostThumbnailFailure(message: String) {

    }

    override fun onGetUserTaggedThumbnailSuccess(response: UserPostThumbnailData) {
        if(response.isSuccess){
            recyclerTagpost(response.result.thumbnailList)
        }
    }

    override fun onGetUserTaggedThumbnailFailure(message: String) {

    }

    override fun onGetUserPostListSuccess(response: UserPostListData) {}
    override fun onGetUserPostListFailure(message: String) {}
    override fun onGetUserTaggedListSuccess(response: UserPostListData) {}
    override fun onGetUserTaggedListFailure(message: String) {}


    private fun recyclerMypost(datas : ArrayList<PostDataThumbnailList>){


        var linking = roomToAdapter()
        val adapter = OthersProfileThumbnailAdapter(datas,linking)
        binding.recyclerOthersprofileThumbnail.layoutManager = GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        binding.recyclerOthersprofileThumbnail.adapter = adapter
    }


    private fun recyclerTagpost(datas : ArrayList<PostDataThumbnailList>){

        var linking = roomToAdapter()
        val adapter = OthersProfileThumbnailAdapter(datas, linking)
        binding.recyclerOthersprofileThumbnail.layoutManager = GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        binding.recyclerOthersprofileThumbnail.adapter = adapter
    }

    fun changeProfile(postId : Int){
        OtherProfileInfo.setpostId(postId)
        OtherProfileInfo.setpoststate(poststate)
        val Activity = activity as MainActivity
        Activity.changeFragment("ProfilePost")
    }



}