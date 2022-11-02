package com.instagram.src.main

import android.os.Bundle
import com.instagram.R
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivityMainBinding
import com.instagram.src.main.ProfilePage.ProfileFragment
import com.instagram.src.main.ProfilePage.ProfileeditFragment
import com.instagram.src.main.ProfilePage.ProfileeditTextFragment
import com.instagram.src.main.home.HomeFragment
import com.instagram.src.main.SearchPage.SearchFragment
import com.instagram.src.main.ShoppingPage.ShoppingFragment
import com.instagram.src.main.VideoPage.VideoFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private var profileeditActivity : ProfileeditFragment ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.itemIconTintList = null

        Jwt.setjwt(intent.getStringExtra("jwt"))


        // BottomNavigationView 구현

        binding.mainBtmNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_btn_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_btn_search -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, SearchFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_btn_video -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, VideoFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_btn_shopping -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ShoppingFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_btn_profile -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ProfileFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.menu_btn_home
        }
    }

    // 프래그먼트간 이동 구현
    fun changeFragment(name : String){
        when(name){

            "Profile"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm, ProfileFragment())
                    .commit()
            }

            "ProfileEdit"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm, ProfileeditFragment())
                    .commit()
            }

            "ProfileEditText"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm, ProfileeditTextFragment())
                    .commit()
            }
        }

    }

}