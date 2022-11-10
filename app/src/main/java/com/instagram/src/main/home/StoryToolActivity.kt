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
    var whoclick = true


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
    }

    override fun onGetStoryDataFailure(message: String) {}


    override fun onPostMakeStorySuccess(response: MakeStoryResponseData) {}
    override fun onPostMakeStoryFailure(message: String) {}
    override fun onGetHomePostDataFailure(message: String) {}
    override fun onGetHomePostDataSuccess(response: PostData) {}
    override fun onGetLikelistFailure(message: String) {}
    override fun onGetLikelistSuccess(response: LikelistData) {}



    fun findindex(){
        var index = 0
        var count = 1
        count = if(currentnick == MyInfo.getnickname()){
            1
        }else{
            2
        }


        // 빈 스토리 제거
        while(true){

            if(index == datas.size) break

            if(datas[index].nickname == MyInfo.getnickname() && count >= 1){
                datas.removeAt(index)
                count--
            }

            if(datas[index].storyDataList.isEmpty() ){
                datas.removeAt(index)
                Log.d("aaaaa", "${datas.toString()}")
            }else{
                index++
            }
        }


        // 클릭한 스토리 기준 데이터 재배치
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

        viewpageStorytool()
    }


    fun viewpageStorytool(){


        var linking = getcontext()


        val adapter = StoryToolAdapter(resultdatas, linking)
        binding.storytoolViewpager.setPageTransformer(ZoomOutPageTransformer())
        binding.storytoolViewpager.adapter = adapter

    }

}