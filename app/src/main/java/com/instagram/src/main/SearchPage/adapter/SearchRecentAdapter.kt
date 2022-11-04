package com.instagram.src.main.SearchPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instagram.databinding.RecyclerRecentsearchBinding

class SearchRecentAdapter(private val datas : Array<String>): RecyclerView.Adapter<SearchRecentAdapter.ViewHolder>(){
    inner class ViewHolder(private val viewBinding : RecyclerRecentsearchBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: String){
            viewBinding.recyclerRecentsearchText.text = item
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerRecentsearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = datas.size
}