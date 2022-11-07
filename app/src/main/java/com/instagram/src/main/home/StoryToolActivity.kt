package com.instagram.src.main.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivityStoryToolBinding
import com.instagram.src.main.home.models.StoryData
import com.instagram.src.main.home.models.StorythumbnailData
import com.instagram.util.ZoomOutPageTransformer
import jp.shts.android.storiesprogressview.StoriesProgressView


class StoryToolActivity : BaseActivity<ActivityStoryToolBinding>(ActivityStoryToolBinding::inflate){

    inner class getcontext{
        val context = this@StoryToolActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = Color.BLACK

        val storyData = intent.getSerializableExtra("storydata")
        val currentnick = intent.getStringExtra("currentNick")

        Log.d("aaaaa", "${storyData.toString()}")
        Log.d("aaaaa", "$currentnick")


    }


//    fun viewpageStorytool(){
//
//
//        var linking = getcontext()
//
//
//        val adapter = StoryToolAdapter(datas, linking)
//        binding.storytoolViewpager.setPageTransformer(ZoomOutPageTransformer())
//        binding.storytoolViewpager.adapter = adapter
//
//    }

}