package com.instagram.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

            val adapter = PostViewAdapter(viewImg)
            viewpager.adapter = adapter

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