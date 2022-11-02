package com.instagram.src.main.ProfilePage

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentProfileeditTextBinding
import com.instagram.src.main.MainActivity

class ProfileeditTextFragment : BaseFragment<FragmentProfileeditTextBinding>(FragmentProfileeditTextBinding::bind, R.layout.fragment_profileedit_text){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cancel = binding.btnProfileeditTextCancel
        val store = binding.btnProfileeditTextStore

        val edit = binding.etProfileeditText


        cancel.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("ProfileEdit")
        }

    }
}