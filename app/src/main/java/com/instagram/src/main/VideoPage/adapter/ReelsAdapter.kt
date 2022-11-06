package com.instagram.src.main.VideoPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.databinding.ViewpageReelsBinding
import com.instagram.src.main.VideoPage.models.ReelsData

class ReelsAdapter(private val datas: ArrayList<ReelsData>) : RecyclerView.Adapter<ReelsAdapter.ViewHolder>() {
    inner class ViewHolder(private val viewBinding: ViewpageReelsBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item:ReelsData){

            val profileimg = viewBinding.recyclerReelsProfileimg
            val video = viewBinding.ivRecyclerReels
            val nick = viewBinding.tvRecyclerReelsNick

            Glide.with(itemView)
                .load(item.profileimg)
                .into(profileimg)

            Glide.with(itemView)
                .load(item.video)
                .into(video)

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