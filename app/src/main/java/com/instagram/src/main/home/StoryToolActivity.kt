package com.instagram.src.main.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivityStoryToolBinding
import com.instagram.src.main.home.adapter.StoryToolAdapter
import com.instagram.src.main.home.models.ImgData
import com.instagram.src.main.home.models.StoryData
import com.instagram.util.ZoomOutPageTransformer
import jp.shts.android.storiesprogressview.StoriesProgressView


class StoryToolActivity : BaseActivity<ActivityStoryToolBinding>(ActivityStoryToolBinding::inflate){

    inner class getcontext{
        val context = this@StoryToolActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = Color.BLACK
        viewpageStorytool()




    }


    fun viewpageStorytool(){

        val temp = ImgData(img = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", time = "12시간")
        val temp2 = ImgData(img = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", time = "6시간")
        val imgdata = arrayListOf<ImgData>(temp,temp2)
        val imgdata2 = arrayListOf<ImgData>(temp,temp2,temp,temp2)
        val data = StoryData(nick = "noah", profile = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", imgData = imgdata)
        val data2 = StoryData(nick = "noah", profile = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", imgData = imgdata2)

        val datas = arrayListOf(data,data2,data,data2,data,data2)

        var linking = getcontext()


        val adapter = StoryToolAdapter(datas, linking)
        binding.storytoolViewpager.setPageTransformer(ZoomOutPageTransformer())
        binding.storytoolViewpager.adapter = adapter

    }

}