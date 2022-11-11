package com.instagram.src.main.home

import android.annotation.SuppressLint
import android.app.appsearch.AppSearchResult.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentHomeBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.MyInfo
import com.instagram.src.main.ProfilePage.ProfileFragmentInterface
import com.instagram.src.main.ProfilePage.ProfileService
import com.instagram.src.main.ProfilePage.models.ModifyProfileData
import com.instagram.src.main.ProfilePage.models.MyProfileData
import com.instagram.src.main.ProfilePage.models.OthersProfileData
import com.instagram.src.main.ProfilePage.models.PostFollowingData
import com.instagram.src.main.home.adapter.PostAdapter
import com.instagram.src.main.home.adapter.StoryThumbnailAdapter
import com.instagram.src.main.home.models.*
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),ProfileFragmentInterface,HomeFragmentInterface, StoryFragmentInterface{


    private var dataCount = 0
    private var nextpage = false
    private var page  = 0
    private val datas = arrayListOf<PostdetialData>()

    private lateinit var passStoryData : ArrayList<StorythumbnailData>

    var realUri : Uri? = null

    private var mystoryState = false
    private var state = true





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

    override fun onStart() {
        super.onStart()

        Log.d("aaaaaa","onStart")

        if(state){
            HomeService(this).tryGetHomePostData(Jwt.getjwt(),page.toString())
            ProfileService(this).tryGetMyProfileData(Jwt.getjwt())
        }

        binding.homeMakestoryBtn.isVisible = false




        // 최하단 스크롤 감지시, getMoreData 실행
        binding.homeScroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if(!v.canScrollVertically(1)){
                Log.d("aaaaa","lastScroll")

                getMoreData()
            }
        }

        // 내 스토리화면 클릭

        binding.homeMystoryBorder.setOnClickListener {
            Log.d("aaaaa", "Clicked")
            if(mystoryState){
                val intent = Intent(context,StoryToolActivity::class.java)
                    .putExtra("currentNick", MyInfo.getnickname())
                startActivity(intent)

            }else{
                openCamera()
            }

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


    private fun recyclerStoryThumbnail(datas : ArrayList<StorythumbnailData>){

        val linking = getcontext()

        val adapter = StoryThumbnailAdapter(datas, linking)
        binding.recyclerHomeStory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerHomeStory.adapter = adapter
    }

    private fun recyclerPost(datas : ArrayList<PostdetialData>){

        if(nextpage){
            binding.recyclerHomeBody.adapter?.notifyItemInserted(datas.size)
            binding.recyclerHomeBody.adapter?.notifyDataSetChanged()
        }

        val linking = getcontext()

        val adapter = PostAdapter(datas, linking)
        binding.recyclerHomeBody.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerHomeBody.adapter = adapter


    }

    override fun onGetMyProfileSuccess(response: MyProfileData) {
        if(response.isSuccess){

            // 프로필 API 성공시,  내 프로필 닉네임 표시

            MyInfo.setprofileimg(response.result.profileUrl)
            MyInfo.setnickname(response.result.nickname)
            MyInfo.setname(response.result.name)
            MyInfo.setwebsite(response.result.link)
            MyInfo.setdescription(response.result.description)

            Glide.with(this)
                .load(response.result.profileUrl)
                .into(binding.homeMyprofileImagebtn)
            binding.homeMynick.text = response.result.nickname
            StoryService(this).tryGetUserStoryData(Jwt.getjwt(),MyInfo.getnickname())
            HomeService(this).tryGetStoryData(Jwt.getjwt())
        }else{
            showCustomToast("프로필 데이터 불러오기 실패")
        }
    }

    override fun onGetMyProfileFailure(message: String) {}


    
    override fun onGetHomePostDataSuccess(response: PostData) {
        if(response.isSuccess){
            dataCount = response.result.postList.size
            page = response.result.page
            for(i in response.result.postList){
                datas.add(i)
            }

            recyclerPost(datas)
        }
    }

    override fun onGetHomePostDataFailure(message: String) {}

    override fun onGetStoryDataSuccess(response: StoryData) {
        passStoryData = response.result

        var index = 0

        // 전체목록중 스토리가 있는 데이터만 필터링

        while(true){

            if(index == passStoryData.size) break

            if(passStoryData[index].storyDataList.isEmpty() || passStoryData[index].nickname == MyInfo.getnickname()){
                passStoryData.removeAt(index)
                Log.d("aaaaa", "${passStoryData.toString()}")
            }else{
                index++
            }
        }

        //
        if(passStoryData.isNotEmpty()){
            recyclerStoryThumbnail(passStoryData)
        }


    }

    override fun onGetStoryDataFailure(message: String) {}
    
    fun getMoreData(){
        nextpage = true
        page++
        
        // page수를 증가시켜서 서버 요청
        HomeService(this).tryGetHomePostData(Jwt.getjwt(),page.toString())
    }


    override fun onGetUserStorySuccess(response: UserStoryData) {
        if(response.isSuccess){
            
            // 내 스토리가 있는지 없는지체크. Home myprofile 레이아웃 변경
            
            if(response.result.storyDataList.isEmpty()){
                mystoryState = false
                binding.homeMakestoryBtn.isVisible = true
                binding.homeMystoryBorder.isVisible = false
            }else{
                mystoryState = true
                binding.homeMakestoryBtn.isVisible = false
                binding.homeMystoryBorder.isVisible = true
            }
        }else{
            showCustomToast("My Story 데이터 불러오기 실패")
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

    override fun onGetUserStoryFailure(message: String) {}
    override fun onGetLikelistSuccess(response: LikelistData) {}
    override fun onGetLikelistFailure(message: String) { }
    override fun onPostMakeStorySuccess(response: MakeStoryResponseData) {}
    override fun onPostMakeStoryFailure(message: String) {}
    override fun onGetOthersProfileSuccess(response: OthersProfileData) {}
    override fun onGetOthersProfileFailure(message: String) {}
    override fun onPatchModifyProfileSuccess(response: ModifyProfileData) {}
    override fun onPatchModifyProfileFailure(message: String) {}
    override fun onPostFollowingSuccess(response: PostFollowingData) {}
    override fun onPostFollowingFailure(message: String) {}
    override fun onPatchunFollowingSuccess(response: PostFollowingData) {}
    override fun onPatchunFollowingFailure(message: String) {}




}