package com.instagram.src.main.ProfilePage

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentProfileeditBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.ProfilePage.models.ModifyProfileBodyData
import com.instagram.src.main.ProfilePage.models.ModifyProfileData
import com.instagram.src.main.ProfilePage.models.MyProfileData

class ProfileeditFragment : BaseFragment<FragmentProfileeditBinding>(FragmentProfileeditBinding::bind, R.layout.fragment_profileedit),ProfileActivityInterface{

    var name : String? = ""
    var nick : String? = ""
    var website : String? = ""
    var description : String? = ""
    var profile : String? = ""

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
        // ProfileeditTextFragment 로 이동시 데이터 넘겨줌
        
        for(i in 0 until arr.size){
            arr[i].setOnClickListener {
                val data = arrayOf(arrHeader[i],arr[i].text.toString(),name,nick,website,description,profile)
                setFragmentResult("fromProfileeditFragment", bundleOf("bundleKey" to data))
                val Activity = activity as MainActivity
                Activity.changeFragment("ProfileEditText")
            }
        }




        
        // ProfileFragment 에서 데이터 넘겨받기
        setFragmentResultListener("fromProfileFragment"){requestKey, bundle ->
            val result = bundle.getStringArray("bundleKey")

            if (result != null) {
                name = result[0]
                nick = result[1]
                website = result[2]
                description = result[3]
                profile = result[4]
                
                editname.setText(name)
                editnick.setText(nick)
                editweb.setText(website)
                editintro.setText(description)

                Glide.with(this)
                    .load(profile)
                    .into(changeimage)
            }
        }

        //ProfileeditTextFragment 에서 Cancel 했을때 넘겨받기
        setFragmentResultListener("fromProfileeditTextFragmentCancel"){requestKey, bundle ->
            val result = bundle.getStringArray("bundleKey")

            if(result != null){
                name = result[0]
                nick = result[1]
                website = result[2]
                description = result[3]
                profile =result[4]

                editname.setText(name)
                editnick.setText(nick)
                editweb.setText(website)
                editintro.setText(description)

                Glide.with(this)
                    .load(profile)
                    .into(changeimage)
            }
        }

        
        // ProfileeditTextFragment 에서 데이터 넘겨받기
        setFragmentResultListener("fromProfileeditTextFragment"){requestKey, bundle ->

            val result = bundle.getStringArray("bundleKey")

            Log.d("aaaa","${result?.get(0)} ${result?.get(1)} ${result?.get(2)} ${result?.get(3)} ${result?.get(4)} ${result?.get(5)}")

            if(result != null){

                name = result[2]
                nick = result[3]
                website = result[4]
                description = result[5]
                profile = result[6]

                editname.setText(name)
                editnick.setText(nick)
                editweb.setText(website)
                editintro.setText(description)

                Glide.with(this)
                    .load(profile)
                    .into(changeimage)

                when(result[0]){
                    arrHeader[0] -> {
                        name = result[1]
                        arr[0].setText(result[1])
                    }
                    arrHeader[1] -> {
                        nick = result[1]
                        arr[1].setText(result[1])
                    }
                    arrHeader[2] -> {
                        website = result[1]
                        arr[2].setText(result[1])
                    }
                    arrHeader[3] -> {
                        description = result[1]
                        arr[3].setText(result[1])
                    }
                }


            }

        }

        // edittext 가 차있으면, editting 막기
        if(editname.text.isNotBlank()) editname.isFocusableInTouchMode = false
        if(editnick.text.isNotBlank()) editnick.isFocusableInTouchMode = false
        if(editweb.text.isNotBlank()) editweb.isFocusableInTouchMode = false
        if(editintro.text.isNotBlank()) editintro.isFocusableInTouchMode = false


        // edittext 편집시 변수에 값 저장

        editname.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                name = editname.text.toString()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        editnick.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nick = editnick.text.toString()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        editweb.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                website = editweb.text.toString()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        editintro.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                description = editintro.text.toString()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })



        cancelbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("Profile")
        }

        storebtn.setOnClickListener {
            val datas = ModifyProfileBodyData(name = name, nickname = nick, link = website, description = description, profileUrl = profile)
            ProfileService(this).tryPatchProfileModifyData(Jwt.getjwt(), datas)
        }

    }

    override fun onPatchModifyProfileSuccess(response: ModifyProfileData) {
        val Activity = activity as MainActivity
        Activity.changeFragment("Profile")
    }

    override fun onPatchModifyProfileFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetMyProfileSuccess(response: MyProfileData) {
        TODO("Not yet implemented")
    }

    override fun onGetMyProfileFailure(message: String) {
        TODO("Not yet implemented")
    }





    fun gotoGallery(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent,2000)
    }

}



