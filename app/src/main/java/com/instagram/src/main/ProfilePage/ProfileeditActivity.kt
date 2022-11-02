package com.instagram.src.main.ProfilePage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.setFragmentResultListener
import com.instagram.R
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivityProfileeditBinding
import com.instagram.src.main.MainActivity

class ProfileeditActivity : BaseActivity<ActivityProfileeditBinding>(ActivityProfileeditBinding::inflate){
    var name : String = ""
    var nick : String = ""
    var website : String = ""
    var introduce : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                val intent = Intent(this,ProfileeditTextActivity::class.java)
                    .putExtra("title",arrHeader[i])
                    .putExtra("data",arr[i].text.toString())
                startActivity(intent)
            }
        }

        // 변경된 항목, 변경된 내용 전달받음
        if(intent.getStringExtra("flag") == "true"){
            val pageName = intent.getStringExtra("pageName")
            val editData = intent.getStringExtra("editData")

            if(pageName == arrHeader[0]){
                arr[0].setText(editData)
            }else if(pageName == arrHeader[1]){
                arr[1].setText(editData)
            }else if(pageName == arrHeader[2]){
                arr[2].setText(editData)
            }else if(pageName == arrHeader[3]){
                arr[3].setText(editData)
            }
        }



        cancelbtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ProfileFragment())
                .commit()
        }

        storebtn.setOnClickListener {
            // API 동작
        }

    }
}

