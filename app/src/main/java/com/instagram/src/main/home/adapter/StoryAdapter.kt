package com.instagram.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.instagram.databinding.RecyclerStoryBinding
import com.instagram.src.main.home.models.StoryData

class StoryAdapter(private val datas : ArrayList<StoryData>) : RecyclerView.Adapter<StoryAdapter.ViewHolder>(){

    inner class ViewHolder(private val viewBinding : RecyclerStoryBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item : StoryData){
            viewBinding.recyclerStoryImagebtn.setImageURI(item.profile.toUri())
            viewBinding.recyclerStoryNick.text = item.nickName
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