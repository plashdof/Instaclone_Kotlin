package com.instagram.src.main.SearchPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.databinding.RecyclerThumbnailBinding
import com.instagram.src.main.SearchPage.models.SearchImgList
import com.instagram.src.main.SearchPage.models.SearchThumbnailData

class SearchThumbnailAdapter(private val datas : ArrayList<SearchImgList>):RecyclerView.Adapter<SearchThumbnailAdapter.ViewHolder>(){
    inner class ViewHolder(private val viewBinding : RecyclerThumbnailBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: SearchImgList){
            val img = viewBinding.recyclerThumbnailImg
            val postId = item.postId

            Glide.with(itemView)
                .load(item.imgUrl)
                .into(img)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerThumbnailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = datas.size
}