package com.instagram.src.main.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.instagram.R
import com.instagram.databinding.RecyclerPostBinding
import com.instagram.src.main.home.models.PostData


class PostAdapter(private val datas: Array<PostData>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    inner class ViewHolder(private val viewBinding: RecyclerPostBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item:PostData){
            val nick = viewBinding.recyclerPostNick
            val profileimg = viewBinding.recylerPostProfileimg

            nick.text = item.nick
            Glide.with(itemView)
                .load(item.profileimg)
                .into(profileimg)

            val viewpager = viewBinding.recyclerPostViewpager
            val viewImg = item.imgdata

            val indicator = viewBinding.recyclerPostIndicator
            indicator.noOfPages = viewImg.size

            val adapter = PostViewAdapter(viewImg)
            viewpager.adapter = adapter

            viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    indicator.onPageChange(position)
                    Log.d("aaaa","selected position : $position")
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })



        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}