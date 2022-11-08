package com.instagram.src.main.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivityStoryToolBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MyInfo
import com.instagram.src.main.home.adapter.StoryToolAdapter
import com.instagram.src.main.home.models.*
import com.instagram.util.ZoomOutPageTransformer
import jp.shts.android.storiesprogressview.StoriesProgressView


class StoryToolActivity : BaseActivity<ActivityStoryToolBinding>(ActivityStoryToolBinding::inflate),HomeFragmentInterface,StoryFragmentInterface{

    inner class getcontext{
        val context = this@StoryToolActivity
    }
    var datas : ArrayList<StorythumbnailData> = arrayListOf()
    var resultdatas : ArrayList<StorythumbnailData> = arrayListOf()
    var currentnick : String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = Color.BLACK

        currentnick = intent.getStringExtra("currentNick")
        Log.d("aaaaa", "$currentnick")

        StoryService(this).tryGetUserStoryData(Jwt.getjwt(),currentnick)


    }




    override fun onGetUserStorySuccess(response: UserStoryData) {
        if(response.result.storyDataList.isNotEmpty()){
            val mydata = StorythumbnailData(profileUrl = "${MyInfo.getprofileimg()}", nickname = "${MyInfo.getnickname()}"
                , visitCnt = response.result.visitCnt, storyDataList = response.result.storyDataList)
            datas.add(mydata)
        }

        HomeService(this).tryGetStoryData(Jwt.getjwt())

    }
    override fun onGetUserStoryFailure(message: String) {}

    override fun onGetStoryDataSuccess(response: StoryData) {

        for(i in response.result){
            datas.add(i)
        }
        findindex()

        viewpageStorytool()
    }

    override fun onGetStoryDataFailure(message: String) {}


    override fun onPostMakeStorySuccess(response: MakeStoryResponseData) {}
    override fun onPostMakeStoryFailure(message: String) {}
    override fun onGetHomePostDataFailure(message: String) {}
    override fun onGetHomePostDataSuccess(response: PostData) {}
    override fun onGetLikelistFailure(message: String) {}
    override fun onGetLikelistSuccess(response: LikelistData) {}


    // 클릭한 스토리 기준 데이터 재배치

    fun findindex(){
        var currentindex = 0
        for(i in 0 until datas.size){
            if(datas[i].nickname == currentnick){
                currentindex = i
            }
        }

        for(i in currentindex  until datas.size){
            resultdatas.add(datas[i])
        }
        
        for(i in 0 until currentindex){
            resultdatas.add(datas[i])
        }
    }


    fun viewpageStorytool(){


        var linking = getcontext()


        val adapter = StoryToolAdapter(resultdatas, linking)
        binding.storytoolViewpager.setPageTransformer(ZoomOutPageTransformer())
        binding.storytoolViewpager.adapter = adapter

    }

}