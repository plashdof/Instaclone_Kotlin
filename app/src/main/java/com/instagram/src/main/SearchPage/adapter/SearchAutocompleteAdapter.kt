package com.instagram.src.main.SearchPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.databinding.RecyclerAutocompleteBinding
import com.instagram.src.main.SearchPage.SearchAutocompleteFragment
import com.instagram.src.main.SearchPage.models.Autocompletelist
import com.instagram.src.main.SearchPage.models.SearchAutocompleteData

class SearchAutocompleteAdapter(private val datas : ArrayList<Autocompletelist>, val linking : SearchAutocompleteFragment.getcontext): RecyclerView.Adapter<SearchAutocompleteAdapter.ViewHolder>(){
    inner class ViewHolder(private val viewBinding : RecyclerAutocompleteBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: Autocompletelist){
            val profile = viewBinding.recyclerAutocompleteProfile
            val nick = viewBinding.recyclerAutocompleteNick
            val name = viewBinding.recyclerAutocompleteName

            nick.text = item.nickname
            name.text = item.name
            Glide.with(itemView)
                .load(item.profileUrl)
                .into(profile)


            viewBinding.recyclerAutocompleteLayout.setOnClickListener {
                linking.gotoLiketoOthersprofile(item.nickname,item.userId)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerAutocompleteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = datas.size
}