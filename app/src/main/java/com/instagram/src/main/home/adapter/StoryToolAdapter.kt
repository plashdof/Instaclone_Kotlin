package com.instagram.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.databinding.ViewpageStoryBinding
import com.instagram.src.main.home.models.StoryData

class StoryToolAdapter(private val datas : ArrayList<StoryData>) : RecyclerView.Adapter<StoryToolAdapter.ViewHolder>(){

    inner class ViewHolder(private val viewBinding: ViewpageStoryBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item : StoryData){
            val profile= viewBinding.storyProfile
            val nick = viewBinding.tvStoryNick
            val time = viewBinding.tvStoryTime
            val img = viewBinding.storyImg


            Glide.with(itemView)
                .load(item.profile)
                .into(profile)

            Glide.with(itemView)
                .load(item.imglist[0].img)
                .into(img)

            nick.text = item.nick
            time.text = item.imglist[0].time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ViewpageStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}