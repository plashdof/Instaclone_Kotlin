package com.instagram.src.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import com.instagram.R
import com.instagram.R.*
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
import com.instagram.src.main.home.LikelistFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private var profileeditActivity : ProfileeditFragment ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.itemIconTintList = null

        Jwt.setjwt(intent.getStringExtra("jwt"))


        // BottomNavigationView 구현

        binding.mainBtmNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_btn_home -> {
                        binding.mainBtmNav.itemBackground = Color.rgb(254,254,254).toDrawable()
                        binding.mainBtmNav.setBackgroundColor(Color.rgb(254,254,254))

                        window.statusBarColor = Color.rgb(254,254,254)
                        window.navigationBarColor = Color.rgb(254,254,254)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .addToBackStack(null)
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_btn_search -> {
                        binding.mainBtmNav.itemBackground = Color.rgb(254,254,254).toDrawable()
                        binding.mainBtmNav.setBackgroundColor(Color.rgb(254,254,254))

                        window.statusBarColor = Color.rgb(254,254,254)
                        window.navigationBarColor = Color.rgb(254,254,254)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, SearchFragment())
                            .addToBackStack(null)
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_btn_video -> {
                        binding.mainBtmNav.itemBackground = Color.BLACK.toDrawable()
                        binding.mainBtmNav.setBackgroundColor(Color.BLACK)

                        window.navigationBarColor = Color.BLACK
                        window.statusBarColor = Color.BLACK
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, VideoFragment())
                            .addToBackStack(null)
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_btn_shopping -> {
                        binding.mainBtmNav.itemBackground = Color.rgb(254,254,254).toDrawable()
                        binding.mainBtmNav.setBackgroundColor(Color.rgb(254,254,254))


                        window.statusBarColor = Color.rgb(254,254,254)
                        window.navigationBarColor = Color.rgb(254,254,254)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ShoppingFragment())
                            .addToBackStack(null)
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_btn_profile -> {
                        binding.mainBtmNav.itemBackground = Color.rgb(254,254,254).toDrawable()
                        binding.mainBtmNav.setBackgroundColor(Color.rgb(254,254,254))

                        window.statusBarColor = Color.rgb(254,254,254)
                        window.navigationBarColor = Color.rgb(254,254,254)
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
        makebtnnav()
    }

    // 프래그먼트간 이동 구현
    fun changeFragment(name : String){
        when(name){
            "Home"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm, HomeFragment())
                    .addToBackStack(null)
                    .commit()
                makebtnnav()
            }

            "Comment"->{
                hidebtnnav()
                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm, CommentFragment())
                    .addToBackStack(null)
                    .commit()
            }

            "Likelist"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm, LikelistFragment())
                    .addToBackStack(null)
                    .commit()

                hidebtnnav()
            }

            "Profile"->{

                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm, ProfileFragment())
                    .addToBackStack(null)
                    .commit()

                makebtnnav()
            }

            "ProfileEdit"->{

                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm, ProfileeditFragment())
                    .addToBackStack(null)
                    .commit()

                hidebtnnav()
            }

            "ProfileEditText"->{

                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm, ProfileeditTextFragment())
                    .addToBackStack(null)
                    .commit()

                hidebtnnav()
            }

            "ProfilePost"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm, ProfilePostFragment())
                    .addToBackStack(null)
                    .commit()
            }

            "ProfileOthers"->{

                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm, OthersProfileFragment())
                    .addToBackStack(null)
                    .commit()

                makebtnnav()
            }

            "Search"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm,SearchFragment())
                    .addToBackStack(null)
                    .commit()
                makebtnnav()
            }

            "SearchTool"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm,SearchToolFragment())
                    .addToBackStack(null)
                    .commit()
                hidebtnnav()
            }

            "SelectGallery"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm,SelectgalleryFragment())
                    .addToBackStack(null)
                    .commit()
            }

            "Shopping"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm,ShoppingFragment())
                    .addToBackStack(null)
                    .commit()
            }

            "ShoppingTool"->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(id.main_frm, ShoppingToolFragment())
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