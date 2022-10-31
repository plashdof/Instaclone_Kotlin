package com.instagram.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentHomeBinding
import com.instagram.src.main.home.models.PostSignUpRequest
import com.instagram.src.main.home.models.SignUpResponse
import com.instagram.src.main.home.models.UserResponse

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}