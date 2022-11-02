package com.instagram.src.main.ProfilePage

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentProfileBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.ProfilePage.adapter.ProfileStoryAdapter
import com.instagram.src.main.ProfilePage.adapter.ProfileThumbnailAdapter
import com.instagram.src.main.ProfilePage.models.MyProfileData
import com.instagram.src.main.home.models.StoryData

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::bind, R.layout.fragment_profile),ProfileActivityInterface{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mypostbtn = binding.btnProfileMypost
        val tagpostbtn = binding.btnProfileTagpost
        val editbtn = binding.btnProfileEdit
        
        // 4.1 API 통신
        // 팔로워수 / 팔로잉수 / 게시물수 / 프로필이미지 / 링크 / 소개글 / 실명 / 닉네임 받아오기
        ProfileService(this).tryGetMyProfileData(Jwt.getjwt())


        mypostbtn.setOnClickListener {
            mypostbtn.setBackgroundResource(R.drawable.shape_signupbtn_active)
            mypostbtn.alpha = 1F

            tagpostbtn.setBackgroundResource(R.drawable.shape_signupbtn)
            tagpostbtn.alpha = 0.3F

            recyclerMypost()
        }


        tagpostbtn.setOnClickListener {

            tagpostbtn.setBackgroundResource(R.drawable.shape_signupbtn_active)
            tagpostbtn.alpha = 1F

            mypostbtn.setBackgroundResource(R.drawable.shape_signupbtn)
            mypostbtn.alpha = 0.3F

            recyclerTagpost()
        }


        // 프로필 편집버튼 클릭시 ProfileeditFragment 로 이동
        editbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("ProfileEdit")
        }

    }

    override fun onGetMyProfileSuccess(response: MyProfileData) {

        binding.tvProfilePostcount.text = response.result.postCount.toString()
        binding.tvProfileFollowerCount.text = response.result.followerCount.toString()
        binding.tvProfileFollowingCount.text = response.result.followingCount.toString()

        binding.tvProfileRealname.text = response.result.name
        binding.tvProfileNickname.text  = response.result.nickname

        val website = response.result.link
        val description = response.result.description

        binding.tvProfileDescription.text = description
        binding.tvProfileWebsite.text = website

        
        // 만약 website 나 소개 가 없다면, 해당공간 없애기
        binding.tvProfileWebsite.isVisible = website.isNotBlank()
        binding.tvProfileDescription.isVisible = description.isNotBlank()


        val profileimg = binding.btnProfileImage

        Glide.with(this)
            .load(response.result.profileUrl)
            .into(profileimg)
    }

    override fun onGetMyProfileFailure(message: String) {
        TODO("Not yet implemented")
    }



    private fun recyclerMypost(){

        val data = arrayListOf<String>("https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",)


        val adapter = ProfileThumbnailAdapter(data)
        binding.recyclerProfileThumbnail.layoutManager = GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        binding.recyclerProfileThumbnail.adapter = adapter
    }


    private fun recyclerTagpost(){
        val data = arrayListOf<String>("https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",)


        val adapter = ProfileThumbnailAdapter(data)
        binding.recyclerProfileThumbnail.layoutManager = GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        binding.recyclerProfileThumbnail.adapter = adapter
    }

    private fun recyclerStory(){

        val data = StoryData(profile = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", nickName = "Noah")
        val datas = arrayListOf<StoryData>(data, data, data, data)


        val adapter = ProfileStoryAdapter(datas)
        binding.recyclerProfileStory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerProfileStory.adapter = adapter
    }

}