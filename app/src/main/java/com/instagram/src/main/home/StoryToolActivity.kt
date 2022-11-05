package com.instagram.src.main.home

import android.graphics.Color
import android.os.Bundle
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivityStoryToolBinding
import com.instagram.src.main.home.adapter.StoryToolAdapter
import com.instagram.src.main.home.models.ImgList
import com.instagram.src.main.home.models.StoryData

class StoryToolActivity : BaseActivity<ActivityStoryToolBinding>(ActivityStoryToolBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = Color.BLACK
        viewpageStorytool()
    }


    fun viewpageStorytool(){

        val temp = ImgList(img = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", time = "12시간")
        val imgdata = arrayListOf<ImgList>(temp,temp)
        val data = StoryData(nick = "noah", profile = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", imglist = imgdata)

        val datas = arrayListOf(data,data,data,data,data,data)



        val adapter = StoryToolAdapter(datas)
        binding.storytoolViewpager.adapter = adapter

    }
}