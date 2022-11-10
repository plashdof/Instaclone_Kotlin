package com.instagram.src.main.ProfilePage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.databinding.RecyclerThumbnailBinding
import com.instagram.src.main.ProfilePage.OthersProfileFragment
import com.instagram.src.main.ProfilePage.models.PostDataThumbnailList

class OthersProfileThumbnailAdapter(private val datas : ArrayList<PostDataThumbnailList>, var link : OthersProfileFragment.roomToAdapter) : RecyclerView.Adapter<OthersProfileThumbnailAdapter.ViewHolder>(){
    inner class ViewHolder(private val viewBinding: RecyclerThumbnailBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item : PostDataThumbnailList){
            val view = viewBinding.recyclerThumbnailImg
            Glide.with(itemView)
                .load(item.thumbnail)
                .into(view)

            view.setOnClickListener{
                link.moveToProfilePost(item.postId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerThumbnailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

}