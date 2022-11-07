package com.instagram.src.main.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.databinding.RecyclerStoryThumbnailBinding
import com.instagram.src.main.home.HomeFragment
import com.instagram.src.main.home.StoryToolActivity
import com.instagram.src.main.home.models.StorythumbnailData

class StoryThumbnailAdapter(private val datas : ArrayList<StorythumbnailData>, var linking : HomeFragment.getcontext? = null) : RecyclerView.Adapter<StoryThumbnailAdapter.ViewHolder>(){

    inner class ViewHolder(private val viewBinding: RecyclerStoryThumbnailBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item : StorythumbnailData){
            val img = viewBinding.recyclerStorythumbnailImagebtn
            val nick = viewBinding.recyclerStoryNick
            val state = viewBinding.recyclerStorythumbnailBorder

            val storydatasize = item.storyDataList.size

            // 프로필 이미지 설정
            Glide.with(itemView)
                .load(item.profileUrl)
                .into(img)

            // 닉네임 설정
            nick.text = item.nickname

            // 읽었는지 여부  visitCnt 와 storydatasize 가 같으면 확인
            if(item.visitCnt == storydatasize){
                state.setBackgroundResource(R.drawable.shape_story)
            }else{
                state.setBackgroundResource(R.drawable.shape_story_unread)
            }


            state.setOnClickListener{
                linking?.gotoStoryTool(item.nickname)
            }

            nick.setOnClickListener{
                linking?.gotoStoryTool(item.nickname)
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

