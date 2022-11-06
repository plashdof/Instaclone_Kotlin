package com.instagram.src.main.ProfilePage

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentProfileBinding
import com.instagram.src.main.Modals.BottomSheetProfilemenu
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.Modals.BottomSheetProfileplus
import com.instagram.src.main.home.adapter.StoryThumbnailAdapter
import com.instagram.src.main.ProfilePage.adapter.ProfileThumbnailAdapter
import com.instagram.src.main.ProfilePage.models.ModifyProfileData
import com.instagram.src.main.ProfilePage.models.MyProfileData
import com.instagram.src.main.home.models.StoryThumbnailData

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::bind, R.layout.fragment_profile),ProfileActivityInterface{

    var name : String? = ""
    var nick : String? = ""
    var website : String? = ""
    var description : String? = ""
    var profile : String? = ""
    var postclick : Boolean = true

    inner class roomToAdapter{
        fun moveToProfilePost(){
            changeProfile()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mypostbtn = binding.btnProfileMypost
        val tagpostbtn = binding.btnProfileTagpost
        val editbtn = binding.btnProfileEdit
        val profileimg = binding.btnProfileImage
        val plusbtn = binding.btnProfilePlus
        val menubtn = binding.btnProfileMenu

        recyclerStory()
        recyclerMypost()
        
        // 4.1 API 통신
        // 팔로워수 / 팔로잉수 / 게시물수 / 프로필이미지 / 링크 / 소개글 / 실명 / 닉네임 받아오기
        ProfileService(this).tryGetMyProfileData(Jwt.getjwt())


        mypostbtn.setOnClickListener {
            mypostbtn.setBackgroundResource(R.drawable.shape_signupbtn_active)
            mypostbtn.alpha = 1F

            tagpostbtn.setBackgroundResource(R.drawable.shape_signupbtn)
            tagpostbtn.alpha = 0.3F

            postclick = true

            recyclerMypost()
        }


        tagpostbtn.setOnClickListener {

            tagpostbtn.setBackgroundResource(R.drawable.shape_signupbtn_active)
            tagpostbtn.alpha = 1F

            mypostbtn.setBackgroundResource(R.drawable.shape_signupbtn)
            mypostbtn.alpha = 0.3F

            postclick = false

            recyclerTagpost()
        }


        // 메뉴버튼 클릭시 Bottom Sheet

        menubtn.setOnClickListener {

            val bottomSheet = BottomSheetProfilemenu()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }

        // plus 버튼 클릭시 Bottom Sheet

        plusbtn.setOnClickListener {
            val bottomSheet = BottomSheetProfileplus()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }


        // 프로필 편집버튼 클릭시 ProfileeditFragment 로 이동
        // 편집데이터도 같이 전송
        
        editbtn.setOnClickListener {
            val data = arrayOf(name, nick, website, description, profile)
            setFragmentResult("fromProfileFragment", bundleOf("bundleKey" to data))
            val Activity = activity as MainActivity
            Activity.changeFragment("ProfileEdit")
        }

    }

    override fun onGetMyProfileSuccess(response: MyProfileData) {

        binding.tvProfilePostcount.text = response.result.postCount.toString()
        binding.tvProfileFollowerCount.text = response.result.followerCount.toString()
        binding.tvProfileFollowingCount.text = response.result.followingCount.toString()



        name = response.result.name
        nick = response.result.nickname
        website = response.result.link
        description = response.result.description
        profile = response.result.profileUrl

        binding.tvProfileRealname.text = name
        binding.tvProfileNickname.text  = nick
        binding.tvProfileDescription.text = description
        binding.tvProfileWebsite.text = website

        
        // 만약 website 나 소개 가 없다면, 해당공간 없애기
        binding.tvProfileWebsite.isVisible = website!!.isNotBlank()
        binding.tvProfileDescription.isVisible = description!!.isNotBlank()

        val profileimg = binding.btnProfileImage

        Glide.with(this)
            .load(response.result.profileUrl)
            .into(profileimg)
    }

    override fun onGetMyProfileFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPatchModifyProfileSuccess(response: ModifyProfileData) {
        TODO("Not yet implemented")
    }

    override fun onPatchModifyProfileFailure(message: String) {
        TODO("Not yet implemented")
    }

    fun changeProfile(){
        setFragmentResult("fromProfileFragment", bundleOf("bundleKey" to postclick))
        val Activity = activity as MainActivity
        Activity.changeFragment("ProfilePost")
    }



    private fun recyclerMypost(){

        val data = arrayListOf<String>("https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://source.unsplash.com/collection/3730086/1080x1080",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://source.unsplash.com/collection/3730086/1080x1080",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp","https://source.unsplash.com/collection/932210/1080x1080",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://source.unsplash.com/collection/932210/1080x1080","https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp","https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            )

        var linking = roomToAdapter()
        val adapter = ProfileThumbnailAdapter(data,linking)
        binding.recyclerProfileThumbnail.layoutManager = GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        binding.recyclerProfileThumbnail.adapter = adapter
    }


    private fun recyclerTagpost(){
        val data = arrayListOf<String>("https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://source.unsplash.com/collection/932210/1080x1080",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",)

        var linking = roomToAdapter()
        val adapter = ProfileThumbnailAdapter(data, linking)
        binding.recyclerProfileThumbnail.layoutManager = GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        binding.recyclerProfileThumbnail.adapter = adapter
    }

    private fun recyclerStory(){

        val data = StoryThumbnailData(profile = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", nickName = "Noah")
        val datas = arrayListOf<StoryThumbnailData>(data, data, data, data,data,data,data,data)


        val adapter = StoryThumbnailAdapter(datas)
        binding.recyclerProfileStory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerProfileStory.adapter = adapter
    }

}