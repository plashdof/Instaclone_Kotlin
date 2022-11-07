package com.instagram.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentLikelistBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.home.adapter.LikelistAdapter
import com.instagram.src.main.home.models.LikelistData
import com.instagram.src.main.home.models.LikelistdetialData
import com.instagram.src.main.home.models.PostData

class LikelistFragment : BaseFragment<FragmentLikelistBinding>(FragmentLikelistBinding::bind, R.layout.fragment_likelist),HomeFragmentInterface {


    inner class getcontext{
        val fragcontext = context

        fun gotoOthersprofile(targetNickname: String?){
            movetoOthersprofilePage(targetNickname)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("fromHome"){requestKey, bundle ->
            var result = bundle.getInt("bundleKey")

            HomeService(this).tryGetLikelist(Jwt.getjwt(),result.toString())
        }

        binding.btnLikelistBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.onBackPressed()
        }


    }

    override fun onGetLikelistSuccess(response: LikelistData) {

        if(response.isSuccess){
            recyclerlikelist(response.result)
        }

    }
    override fun onGetLikelistFailure(message: String) {}

    fun recyclerlikelist(datas : ArrayList<LikelistdetialData>){

        val linking = getcontext()

        val adapter = LikelistAdapter(datas, linking)
        binding.recyclerLikelist.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.recyclerLikelist.adapter = adapter
    }

    fun movetoOthersprofilePage(targetNickname : String?){
        val Activity = activity as MainActivity
        setFragmentResult("fromHome", bundleOf("bundleKey" to targetNickname))
        Activity.changeFragment("ProfileOthers")
    }

    override fun onGetHomePostDataSuccess(response: PostData) {}
    override fun onGetHomePostDataFailure(message: String) {}

}