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

class OthersProfileFragment : BaseFragment<FragmentProfileOthersBinding>(FragmentProfileOthersBinding::bind, R.layout.fragment_profile_others),ProfileFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("fromHome"){requestKey, bundle ->
            val result = bundle.getString("bundleKey")

            ProfileService(this).tryGetOthersProfileData(Jwt.getjwt(), result)
        }

        binding.btnOthersprofileBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.onBackPressed()
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

            if(myfollowing == "Y"){
                binding.btnOthersprofileFollowbtn.setBackgroundResource(R.drawable.shape_et)
                binding.btnOthersprofileFollowbtn.text = "팔로잉"
                binding.btnOthersprofileFollowbtn.setTextColor(Color.BLACK)
            }else{
                binding.btnOthersprofileFollowbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
                binding.btnOthersprofileFollowbtn.text = "팔로우"
                binding.btnOthersprofileFollowbtn.setTextColor(Color.WHITE)
            }

            if(!togetherlist.isEmpty()){
                binding.layoutOthersprofileFollowtogether.isVisible = true
                Glide.with(this)
                    .load(response.result.followTogetherList[0].profileUrl)
                    .into(binding.followtogetherImg)
                binding.followtogetherCount.text = (response.result.followTogetherList.size - 1).toString()
                binding.followtogetherNick.text = response.result.followTogetherList[0].nickname
            }else{
                binding.layoutOthersprofileFollowtogether.isVisible = false
            }

        }

    }

    override fun onGetOthersProfileFailure(message: String) {}

    override fun onGetMyProfileSuccess(response: MyProfileData) {}
    override fun onGetMyProfileFailure(message: String) {}
    override fun onPatchModifyProfileSuccess(response: ModifyProfileData) {}
    override fun onPatchModifyProfileFailure(message: String) {}


}