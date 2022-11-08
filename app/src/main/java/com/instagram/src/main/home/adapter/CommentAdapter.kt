package com.instagram.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.databinding.RecyclerCommentBinding
import com.instagram.src.main.home.models.CommentdetailData

class CommentAdapter(private val datas: ArrayList<CommentdetailData>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    inner class ViewHolder(private val viewBinding: RecyclerCommentBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item:CommentdetailData){
            val commentNum = item.commentNum
            val nickname = viewBinding.recyclerCommentNick
            val content = viewBinding.recyclerCommentText
            val profileimg = viewBinding.recyclerCommentProfile
            val time = viewBinding.recyclerCommentTime
            val layout = viewBinding.recyclerCommentLayout
            val commentlikebtn = viewBinding.commentLikebtn
            val commentlikecount = viewBinding.recyclerCommentLikecount

            Glide.with(itemView)
                .load(item.userImg)
                .into(profileimg)
            time.text = item.time
            content.text = item.content
            nickname.text = item.nickname

            if(commentNum == -1){
                val param = layout.layoutParams  as ViewGroup.MarginLayoutParams
                param.setMargins(200,50,50,0)
                layout.layoutParams =param
            }

            // 스토리 여부
            if(item.storyExist == "Activate"){
                profileimg.setBackgroundResource(R.drawable.shape_story_unread)
            }else{
                profileimg.setBackgroundResource(R.drawable.shape_story)
            }
            
            // 좋아요 개수
            if(item.likeCount == 0){
                commentlikecount.isVisible = false
            }else{
                commentlikecount.text = "좋아요 ${item.likeCount}개"
            }

            //나의 좋아요 여부

            if(item.myLike == 1){
                Glide.with(itemView)
                    .load(R.drawable.home_like)
                    .into(commentlikebtn)
            }else{
                Glide.with(itemView)
                    .load(R.drawable.home_unlike)
                    .into(commentlikebtn)
            }




        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}