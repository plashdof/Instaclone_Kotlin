package com.instagram.src.main.home

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentMakepostBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.home.API.CommentAPI
import com.instagram.src.main.home.API.MakePostAPI
import com.instagram.src.main.home.models.MakePostData
import com.instagram.src.main.home.models.PostlikeData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MakepostFragment : BaseFragment<FragmentMakepostBinding>(FragmentMakepostBinding::bind, R.layout.fragment_makepost) {

    var imageuri : Uri? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("fromCamera"){requestKey, bundle ->
            imageuri = bundle.getParcelable<Uri?>("bundleKey")
            binding.makepostImg.setImageURI(imageuri)
        }

        binding.btnMakepost.setOnClickListener {
            val buildLikeRetro = Retrofit.Builder()
                .baseUrl("https://prod.lukechoi.shop/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api = buildLikeRetro.create(MakePostAPI::class.java)

            val imgarr = arrayListOf<String>(imageuri.toString())
            val usertag = arrayListOf<Int>(27,28)
            val hastag = arrayListOf<String>("노아", "라이징")
            val data = MakePostData(content = binding.makepostEt.text.toString(), imgarr, userTagList = usertag, hasTagList = hastag)


            api.makePost(Jwt.getjwt(),data)
                .enqueue(object: Callback<PostlikeData> {
                    override fun onResponse(
                        call: Call<PostlikeData>,
                        response: Response<PostlikeData>
                    ) {
                        Log.d("API결과","${response.body().toString()}")
                        Log.d("API결과","${response.raw()}")
                        val Activity = activity as MainActivity
                        Activity.changeFragment("Home")
                    }
                    override fun onFailure(call: Call<PostlikeData>, t: Throwable) {
                        Log.d("API결과", "실패 : $t")
                    }
                })
        }
    }
}