package com.instagram.src.main.VideoPage.adapter

import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.databinding.ViewpageReelsBinding
import com.instagram.src.main.VideoPage.VideoFragment
import com.instagram.src.main.VideoPage.models.ReelsData
import com.instagram.src.main.home.HomeFragment

class ReelsAdapter(private val datas: ArrayList<ReelsData>, private val linking : VideoFragment.getcontext) : RecyclerView.Adapter<ReelsAdapter.ViewHolder>() {
    inner class ViewHolder(private val viewBinding: ViewpageReelsBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item:ReelsData){

            val profileimg = viewBinding.recyclerReelsProfileimg
            val video = viewBinding.ivRecyclerReels
            val nick = viewBinding.tvRecyclerReelsNick

            Glide.with(itemView)
                .load(item.profileimg)
                .into(profileimg)

            val uri = Uri.parse(item.video)
            video.setVideoURI(uri)
            video.requestFocus()
            video.setOnPreparedListener {
                video.start()
            }

            nick.text = item.nick

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ViewpageReelsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}