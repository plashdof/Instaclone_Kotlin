package com.instagram.src.main.VideoPage

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentVideoBinding
import com.instagram.src.main.VideoPage.adapter.ReelsAdapter
import com.instagram.src.main.VideoPage.models.ReelsData

class VideoFragment : BaseFragment<FragmentVideoBinding>(FragmentVideoBinding::bind, R.layout.fragment_video) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        recyclerReels()
    }

    fun recyclerReels(){

        val data = ReelsData(profileimg = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", nick = "noah", video = "https://source.unsplash.com/collection/932210/1080x1080")
        val data2 = ReelsData(profileimg = "https://source.unsplash.com/collection/3730086/1080x1080", nick = "noah", video = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp")
        val data3 = ReelsData(profileimg = "https://source.unsplash.com/collection/932210/1080x1080", nick = "noah", video = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp")
        val datas = arrayListOf<ReelsData>(data,data3,data2,data)
        val adapter = ReelsAdapter(datas)
        binding.reelsViewpager.orientation = ViewPager2.ORIENTATION_VERTICAL
        binding.reelsViewpager.adapter = adapter
    }
}