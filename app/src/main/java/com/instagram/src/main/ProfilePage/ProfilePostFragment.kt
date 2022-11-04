package com.instagram.src.main.ProfilePage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentProfilePostBinding
import com.instagram.src.main.MainActivity
import com.instagram.src.main.home.adapter.PostAdapter
import com.instagram.src.main.home.models.PostData

class ProfilePostFragment : BaseFragment<FragmentProfilePostBinding>(FragmentProfilePostBinding::bind, R.layout.fragment_profile_post){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // ProfileFragment 에서 썸네일 클릭시,  태그된게시물인지  내게시물인지 구분!
        
        setFragmentResultListener("fromProfileFragment"){requestKey, bundle ->
            val result = bundle.getBoolean("bundleKey")

            if(result) {
                binding.tvProfilepostHeader.text = "게시물"
            } else{
                binding.tvProfilepostHeader.text = "태그됨"
            }
        }

        // 뒤로가기
        
        binding.btnProfilepostBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("Profile")
        }

        recyclerPost()
    }



    private fun recyclerPost(){
        val viewdata = arrayOf<String>("https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp",
            "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp")

        val data = PostData(imgdata = viewdata, nick = "noah", profileimg = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp")
        val data2 = PostData(imgdata = viewdata, nick = "noa", profileimg = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp")

        val datas = arrayOf(data,data2,data,data2)

        val adapter = PostAdapter(datas)
        binding.recyclerProfilepost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerProfilepost.adapter = adapter
    }
}