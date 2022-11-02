package com.instagram.src.main.ProfilePage

import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentProfileeditBinding
import com.instagram.src.main.MainActivity

class ProfileeditFragment : BaseFragment<FragmentProfileeditBinding>(FragmentProfileeditBinding::bind, R.layout.fragment_profileedit){

    var name : String = ""
    var nick : String = ""
    var website : String = ""
    var introduce : String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editname = binding.etProfileeditName
        val editnick = binding.etProfileeditNickname
        val editweb = binding.etProfileeditWebsite
        val editintro = binding.etProfileeditIntroduce

        val cancelbtn = binding.btnProfileeditCancel
        val storebtn = binding.btnProfileeditStore
        val changeimage = binding.btnProfileeditChangeimage

        val arr = arrayListOf<EditText>(editname, editnick, editweb, editintro)

        for(i in arr){
            i.setOnClickListener {

                val Activity = activity as MainActivity
                Activity.changeFragment("ProfileEditText")
            }
        }

        cancelbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("Profile")
        }
        
        storebtn.setOnClickListener { 
            // API 동작
        }





    }



}