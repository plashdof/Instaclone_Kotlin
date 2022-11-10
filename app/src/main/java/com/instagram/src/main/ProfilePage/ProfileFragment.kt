package com.instagram.src.main.ProfilePage

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
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
import com.instagram.src.main.MyInfo
import com.instagram.src.main.home.adapter.StoryThumbnailAdapter
import com.instagram.src.main.ProfilePage.adapter.ProfileThumbnailAdapter
import com.instagram.src.main.ProfilePage.models.*
import com.instagram.src.main.home.models.StorythumbnailData
import java.text.SimpleDateFormat

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::bind, R.layout.fragment_profile),ProfilePostFragmentInterface{

    var name : String? = ""
    var nick : String? = ""
    var website : String? = ""
    var description : String? = ""
    var profile : String? = ""
    var postclick : Boolean = true

    var realUri : Uri? = null
    var state = true

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

//        recyclerStory()
        recyclerMypost()
        
        // 4.1 API 통신
        // 팔로워수 / 팔로잉수 / 게시물수 / 프로필이미지 / 링크 / 소개글 / 실명 / 닉네임 받아오기
        if(state){
            ProfileService(this).tryGetMyProfileData(Jwt.getjwt())
        }



        mypostbtn.setOnClickListener {
            mypostbtn.setBackgroundResource(R.drawable.shape_signupbtn_active)
            mypostbtn.alpha = 1F

            tagpostbtn.setBackgroundResource(R.drawable.shape_signupbtn)
            tagpostbtn.alpha = 0.3F

            postclick = true

            ProfilePostService(this).tryGetUserPostThumbnail(Jwt.getjwt(),MyInfo.getuserId(),0)
        }


        tagpostbtn.setOnClickListener {

            tagpostbtn.setBackgroundResource(R.drawable.shape_signupbtn_active)
            tagpostbtn.alpha = 1F

            mypostbtn.setBackgroundResource(R.drawable.shape_signupbtn)
            mypostbtn.alpha = 0.3F

            postclick = false

            ProfilePostService(this).tryGetUserTaggedThumbnail(Jwt.getjwt(),MyInfo.getuserId(),0)
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

        profileimg.setOnClickListener {
            openCamera()
        }

    }
    // 스토리 작성 위해 카메라앱으로 이동

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        state = false
        Log.d("aaaaaa","openCamera")

        createImageUri(newFileName(), "image/jpg")?.let { uri ->
            realUri = uri
            // MediaStore.EXTRA_OUTPUT을 Key로 하여 Uri를 넘겨주면
            // 일반적인 Camera App은 이를 받아 내가 지정한 경로에 사진을 찍어서 저장시킨다.
            intent.putExtra(MediaStore.EXTRA_OUTPUT, realUri)
            intent.also{
                childForResult.launch(it)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun newFileName(): String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "$filename.jpg"
    }

    private fun createImageUri(filename: String, mimeType: String): Uri? {
        val Activity = activity as MainActivity
        var values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)
        return Activity.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    }

    private val childForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val Activity = activity as MainActivity
            Log.d("aaaaaa","$realUri")
            setFragmentResult("fromCamera", bundleOf("bundleKey" to realUri))
            Activity.changeFragment("Makestory")
        }

    override fun onGetMyProfileSuccess(response: MyProfileData) {

        if(response.isSuccess){

            // 싱글톤객체에 프로필정보 저장
            MyInfo.setnickname(response.result.nickname)
            MyInfo.setprofileimg(response.result.profileUrl)

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
        }else{
            showCustomToast("프로필 불러오기 실패")
        }
        
    }

    override fun onGetMyProfileFailure(message: String) {}

    override fun onGetOthersProfileSuccess(response: OthersProfileData) {}
    override fun onGetOthersProfileFailure(message: String) {}

    override fun onPatchModifyProfileSuccess(response: ModifyProfileData) {}
    override fun onPatchModifyProfileFailure(message: String) {}

    override fun onPostFollowingSuccess(response: PostFollowingData) {}
    override fun onPostFollowingFailure(message: String) {}

    override fun onPatchunFollowingSuccess(response: PostFollowingData) {}
    override fun onPatchunFollowingFailure(message: String) {}

    fun changeProfile(){
        setFragmentResult("fromProfileFragment", bundleOf("bundleKey" to postclick))
        val Activity = activity as MainActivity
        Activity.changeFragment("ProfilePost")
    }


    override fun onGetUserPostThumbnailSuccesss(response: UserPostThumbnailData) {}
    override fun onGetUserPostThumbnailFailure(message: String) {}

    override fun onGetUserTaggedThumbnailSuccess(response: UserPostThumbnailData) {}
    override fun onGetUserTaggedThumbnailFailure(message: String) {}

    override fun onGetUserTaggedListSuccess(response: UserPostListData) { }
    override fun onGetUserTaggedListFailure(message: String) {}
    override fun onGetUserPostListSuccess(response: UserPostListData) {}
    override fun onGetUserPostListFailure(message: String) {}



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

//    private fun recyclerStory(){
//
//        val data = StorythumbnailData(profileUrl = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", nickname = "Noah", visitCnt = 0)
//        val datas = arrayListOf<StorythumbnailData>(data, data, data, data,data,data,data,data)
//
//
//        val adapter = StoryThumbnailAdapter(datas)
//        binding.recyclerProfileStory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//        binding.recyclerProfileStory.adapter = adapter
//    }

}