package com.instagram.src.main.ProfilePage.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.databinding.RecyclerSelectgalleryBinding

class SelectGalleryAdapter(private val datas : Array<Uri?>) : RecyclerView.Adapter<SelectGalleryAdapter.ViewHolder>(){
    inner class ViewHolder(private val viewBinding: RecyclerSelectgalleryBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item : Uri?){
            val img = viewBinding.recyclerSelectgalleryImg

            Glide.with(itemView)
                .load(item)
                .into(img)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerSelectgalleryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

}