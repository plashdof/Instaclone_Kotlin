package com.instagram.src.main.ProfilePage

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentProfileOthersBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.ProfilePage.models.ModifyProfileData
import com.instagram.src.main.ProfilePage.models.MyProfileData
import com.instagram.src.main.ProfilePage.models.OthersProfileData
import com.instagram.src.main.ProfilePage.models.PostFollowingData

class OthersProfileFragment : BaseFragment<FragmentProfileOthersBinding>(FragmentProfileOthersBinding::bind, R.layout.fragment_profile_others),ProfileFragmentInterface {


    var userId : String? = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("fromHome"){requestKey, bundle ->
            val result = bundle.getStringArray("bundleKey")

            val targetNick = result?.get(0)
            userId = result?.get(1)
            ProfileService(this).tryGetOthersProfileData(Jwt.getjwt(), targetNick)
        }

        binding.btnOthersprofileBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.onBackPressed()
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


}