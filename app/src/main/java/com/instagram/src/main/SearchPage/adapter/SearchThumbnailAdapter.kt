package com.instagram.src.main.SearchPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.databinding.RecyclerThumbnailBinding
import com.instagram.src.main.SearchPage.models.SearchThumbnailData

class SearchThumbnailAdapter(private val datas : Array<SearchThumbnailData>):RecyclerView.Adapter<SearchThumbnailAdapter.ViewHolder>(){
    inner class ViewHolder(private val viewBinding : RecyclerThumbnailBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: SearchThumbnailData){
            val img = viewBinding.recyclerThumbnailImg
            val id = item.id

            Glide.with(itemView)
                .load(item.img)
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