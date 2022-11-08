package com.instagram.src.main.home

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentMakestoryBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.home.models.*

class MakestoryFragment : BaseFragment<FragmentMakestoryBinding>(FragmentMakestoryBinding::bind, R.layout.fragment_makestory),StoryFragmentInterface {

    private var imageuri : Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.makestoryBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("Home")
        }

        binding.makestoryNextbtn.setOnClickListener {
            val data = arrayListOf<Nicknames>()
            val datas = PostMakeStoryData(storyImgUrl = imageuri.toString(), userTagList = data)
            StoryService(this).tryPostMakeStoryData(Jwt.getjwt(),datas)
        }
    }
    override fun onStart() {
        super.onStart()

        setFragmentResultListener("fromCamera"){requestKey, bundle ->
            imageuri = bundle.getParcelable<Uri?>("bundleKey")
            binding.makestoryBackground.setImageURI(imageuri)
        }
    }

    override fun onPostMakeStorySuccess(response: MakeStoryResponseData) {
        if(!response.isSuccess){
            showCustomToast("스토리 업로드 실패")
        }
        val Activity = activity as MainActivity
        Activity.changeFragment("Home")
    }
    override fun onPostMakeStoryFailure(message: String) {

    }

    override fun onGetUserStoryFailure(message: String) {
        TODO("Not yet implemented")
    }
    override fun onGetUserStorySuccess(response: UserStoryData) {
        TODO("Not yet implemented")
    }


}