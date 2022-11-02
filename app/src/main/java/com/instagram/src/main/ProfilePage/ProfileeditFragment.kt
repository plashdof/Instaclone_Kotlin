package com.instagram.src.main.ProfilePage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
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



        // Fragment 데이터 이동간, 데이터 매칭을 위해,  Array로 indexing 활용
        val arr = arrayListOf<EditText>(editname, editnick, editweb, editintro)
        val arrHeader = arrayListOf<String>("이름", "사용자 이름", "웹사이트", "소개")

        // 각각의 edittext clicklistner for문으로 구현
        for(i in 0 until arr.size){
            arr[i].setOnClickListener {
                val data = arrayOf(arrHeader[i],arr[i].text.toString())
                setFragmentResult("headerText", bundleOf("bundleKey" to data))
                val Activity = activity as MainActivity
                Activity.changeFragment("ProfileEditText")
            }
        }

        // 변경된 항목, 변경된 내용 전달받음
        setFragmentResultListener("changeText"){requestKey, bundle ->

            val result = bundle.getStringArrayList("bundleKey")

            Log.d("aaaaaa","${result?.get(0)}  ${result?.get(1)}")

            if(result?.get(0) == arrHeader[0]){
                arr[0].setText(result?.get(1))
            }else if(result?.get(0) == arrHeader[1]){
                arr[1].setText(result?.get(1))
            }else if(result?.get(0) == arrHeader[2]){
                arr[2].setText(result?.get(1))
            }else if(result?.get(0) == arrHeader[3]){
                arr[3].setText(result?.get(1))
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