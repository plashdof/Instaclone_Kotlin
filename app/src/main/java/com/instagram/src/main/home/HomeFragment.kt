package com.instagram.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentHomeBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.home.adapter.PostAdapter
import com.instagram.src.main.home.adapter.StoryThumbnailAdapter
import com.instagram.src.main.home.models.PostData
import com.instagram.src.main.home.models.PostdetialData
import com.instagram.src.main.home.models.StoryThumbnailData

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),HomeFragmentInterface{


    inner class getcontext{
        val fragcontext = context
    }

    override fun onStart() {
        super.onStart()
        Log.d("ddddd","onStart")

        HomeService(this).tryGetHomePostData(Jwt.getjwt(),"0")

        recyclerStoryThumbnail()

    }

    override fun onGetHomePostDataSuccess(response: PostData) {
        recyclerPost(response.result.postList)

    }

    override fun onGetHomePostDataFailure(message: String) {
    }



    private fun recyclerStoryThumbnail(){

        val data = StoryThumbnailData(profile = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", nickName = "Noah", state = false)
        val data2 = StoryThumbnailData(profile = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", nickName = "Noah", state = true)
        val datas = arrayListOf<StoryThumbnailData>(data, data2, data, data2,data,data2,data,data2)


        val linking = getcontext()

        val adapter = StoryThumbnailAdapter(datas, linking)
        binding.recyclerHomeStory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerHomeStory.adapter = adapter
    }

    private fun recyclerPost(datas : ArrayList<PostdetialData>){
        Log.d("ddddd","recyclerPost")
        val adapter = PostAdapter(datas)
        binding.recyclerHomeBody.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerHomeBody.adapter = adapter
    }

}