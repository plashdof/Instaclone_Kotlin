package com.instagram.src.main.home.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.databinding.RecyclerStoryThumbnailBinding
import com.instagram.src.main.home.HomeFragment
import com.instagram.src.main.home.StoryToolActivity
import com.instagram.src.main.home.models.StoryThumbnailData

class StoryThumbnailAdapter(private val datas : ArrayList<StoryThumbnailData>, var linking : HomeFragment.getcontext? = null) : RecyclerView.Adapter<StoryThumbnailAdapter.ViewHolder>(){

    inner class ViewHolder(private val viewBinding: RecyclerStoryThumbnailBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item : StoryThumbnailData){
            val img = viewBinding.recyclerStorythumbnailImagebtn
            val nick = viewBinding.recyclerStoryNick
            val state = viewBinding.recyclerStorythumbnailBorder

            Glide.with(itemView)
                .load(item.profile)
                .into(img)

            nick.text = item.nickName

            if(item.state == false){
                state.setBackgroundResource(R.drawable.shape_story_unread)
            }else{
                state.setBackgroundResource(R.drawable.shape_story)
            }

            state.setOnClickListener{
                val intent = Intent(linking?.fragcontext, StoryToolActivity::class.java)
                intent.run{ linking?.fragcontext?.startActivity(this) }
            }

            nick.setOnClickListener{
                val intent = Intent(linking?.fragcontext, StoryToolActivity::class.java)
                intent.run{ linking?.fragcontext?.startActivity(this) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerStoryThumbnailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}

