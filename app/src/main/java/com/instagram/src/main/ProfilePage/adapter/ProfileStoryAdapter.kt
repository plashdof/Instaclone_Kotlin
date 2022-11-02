package com.instagram.src.main.ProfilePage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.databinding.RecyclerStoryBinding
import com.instagram.src.main.home.models.StoryData

class ProfileStoryAdapter(private val datas : ArrayList<StoryData>) : RecyclerView.Adapter<ProfileStoryAdapter.ViewHolder>(){

    inner class ViewHolder(private val viewBinding: RecyclerStoryBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item : StoryData){
            val img = viewBinding.recyclerStoryImagebtn
            val nick = viewBinding.recyclerStoryNick

            Glide.with(itemView)
                .load(item.profile)
                .into(img)

            nick.text = item.nickName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}

