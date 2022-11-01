package com.instagram.src.main.SignupPages

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.core.view.get
import com.instagram.R
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivitySignupbirthBinding

class SignupbirthActivity : BaseActivity<ActivitySignupbirthBinding>(ActivitySignupbirthBinding::inflate){

    var year : String = ""
    var month : String = ""
    var dayOfMonth : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.none, R.anim.none)

        val dp = binding.dpSignupbirth
        val text = binding.etSignupbirthBirth
        val nextbtn = binding.btnSignupnickNext

        val name = intent.getStringExtra("name")
        val pw = intent.getStringExtra("pw")
        val userKey = intent.getStringExtra("userKey")

        val calendar = Calendar.getInstance()

        text.text = " ${calendar.get(Calendar.YEAR)}년 ${calendar.get(Calendar.MONTH)}월 ${calendar.get(Calendar.DAY_OF_MONTH)}일"




        dp.setOnDateChangedListener { datePicker, i, i2, i3 ->
            year = i.toString()

            month = if(i2 < 10){
                "0${i2+1}"
            }else{
                i2.toString()
            }

            dayOfMonth = if(i3 < 10){
                "0$i3"
            }else{
                i3.toString()
            }

            text.text = " ${i}년 ${i2+1}월 ${i3}일"
        }


        nextbtn.setOnClickListener {
            val intent = Intent(this, SignupnickActivity::class.java)
                .putExtra("name", name)
                .putExtra("pw", pw)
                .putExtra("birth", "$year-$month-$dayOfMonth")
                .putExtra("userKey", userKey)

            startActivity(intent)
        }

    }
}