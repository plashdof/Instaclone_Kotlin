package com.instagram.src.main.ShoppingPage.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.databinding.RecyclerShoppingThumbnailBinding
import com.instagram.src.main.ShoppingPage.models.ShoppingThumbnailData

class ShoppingThumbnailAdapter(private val datas: ArrayList<ShoppingThumbnailData>) : RecyclerView.Adapter<ShoppingThumbnailAdapter.ViewHolder>() {
    inner class ViewHolder(private val viewBinding: RecyclerShoppingThumbnailBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: ShoppingThumbnailData){

            val img = viewBinding.recyclerShoppingImg

            Log.d("aaaaa", "????")
            Glide.with(itemView)
                .load(item.img)
                .into(img)


        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerShoppingThumbnailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}