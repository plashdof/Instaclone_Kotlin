package com.instagram.src.main.home.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.databinding.RecyclerLikelistBinding
import com.instagram.src.main.home.HomeFragment
import com.instagram.src.main.home.LikelistFragment
import com.instagram.src.main.home.models.LikelistdetialData

class LikelistAdapter(private val datas: ArrayList<LikelistdetialData>, var linking : LikelistFragment.getcontext? = null) : RecyclerView.Adapter<LikelistAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewBinding: RecyclerLikelistBinding) : RecyclerView.ViewHolder(viewBinding.root){

        fun bind(item:LikelistdetialData){
            val profileimg = viewBinding.recyclerLikelistProfileimg
            val nickname = viewBinding.recyclerLikelistNick
            val name = viewBinding.recyclerLikelistName
            val followbtn = viewBinding.recyclerLikelistFollowbtn
            val userId = item.userId

            Log.d("aaaaa","데이터들 ${item.userImg} ${item.nickname} ${item.name} ${item.followState} ${item.storyExit}")

            Glide.with(itemView)
                .load(item.userImg)
                .into(profileimg)
            nickname.text= item.nickname
            name.text = item.name

            viewBinding.recyclerLikelistBorder.isVisible = item.storyExit == "Y"

            if(item.followState == "팔로잉"){
                followbtn.setBackgroundResource(R.drawable.shape_et)
                followbtn.setTextColor(Color.BLACK)
            }else{
                followbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
                followbtn.setTextColor(Color.WHITE)
            }
            
            // 프로필사진. 닉네임 클릭시 해당 유저 프로필페이지 이동

            profileimg.setOnClickListener {
                linking?.gotoOthersprofile(item.nickname)
            }
            nickname.setOnClickListener {
                linking?.gotoOthersprofile(item.nickname)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerLikelistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}