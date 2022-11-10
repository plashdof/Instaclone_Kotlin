package com.instagram.src.main.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.databinding.ViewpagePostBinding
import com.instagram.src.main.home.models.ImgData

class PostViewAdapter(private val datas: ArrayList<ImgData>) : RecyclerView.Adapter<PostViewAdapter.ViewHolder>() {
    inner class ViewHolder(private val viewBinding: ViewpagePostBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item:ImgData){
            val img = viewBinding.viewpageImg

            Log.d("ddddd","${item.imgUrl}")
            Glide.with(itemView)
                .load(item.imgUrl)
                .into(img)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ViewpagePostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}