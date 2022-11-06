package com.instagram.src.main

import android.os.Bundle
import androidx.core.view.isVisible
import com.instagram.R
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivityMainBinding
import com.instagram.src.main.ProfilePage.*
import com.instagram.src.main.home.HomeFragment
import com.instagram.src.main.SearchPage.SearchFragment
import com.instagram.src.main.SearchPage.SearchRecentsearchFragment
import com.instagram.src.main.SearchPage.SearchToolFragment
import com.instagram.src.main.ShoppingPage.ShoppingFragment
import com.instagram.src.main.ShoppingPage.ShoppingToolFragment
import com.instagram.src.main.VideoPage.VideoFragment
import com.instagram.src.main.home.CommentFragment

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
                            .addToBackStack(null)
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_btn_search -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, SearchFragment())
                            .addToBackStack(null)
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_btn_video -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, VideoFragment())
                            .addToBackStack(null)
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_btn_shopping -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ShoppingFragment())
                            .addToBackStack(null)
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_btn_profile -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ProfileFragment())
                            .addToBackStack(null)
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.menu_btn_home
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    // 프래그먼트간 이동 구현
    fun changeFragment(name : String){
        when(name){
            "Home"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm, HomeFragment())
                    .addToBackStack(null)
                    .commit()
                makebtnnav()
            }

            "Comment"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm, CommentFragment())
                    .addToBackStack(null)
                    .commit()
                hidebtnnav()
            }

            "Profile"->{

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm, ProfileFragment())
                    .addToBackStack(null)
                    .commit()

                makebtnnav()
            }

            "ProfileEdit"->{

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm, ProfileeditFragment())
                    .addToBackStack(null)
                    .commit()

                hidebtnnav()
            }

            "ProfileEditText"->{

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm, ProfileeditTextFragment())
                    .addToBackStack(null)
                    .commit()

            }

            "ProfilePost"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm, ProfilePostFragment())
                    .addToBackStack(null)
                    .commit()
            }

            "Search"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm,SearchFragment())
                    .addToBackStack(null)
                    .commit()
            }

            "SearchTool"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm,SearchToolFragment())
                    .addToBackStack(null)
                    .commit()
            }

            "SelectGallery"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm,SelectgalleryFragment())
                    .addToBackStack(null)
                    .commit()
            }

            "Shopping"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm,ShoppingFragment())
                    .addToBackStack(null)
                    .commit()
            }

            "ShoppingTool"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frm, ShoppingToolFragment())
                    .addToBackStack(null)
                    .commit()
            }

        }
    }

    fun hidebtnnav(){
        binding.mainBtmNav.isVisible = false
    }

    fun makebtnnav(){
        binding.mainBtmNav.isVisible = true
    }

}