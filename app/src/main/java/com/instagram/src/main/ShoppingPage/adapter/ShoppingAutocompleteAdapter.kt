package com.instagram.src.main.ShoppingPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.databinding.RecyclerAutocompleteBinding
import com.instagram.src.main.ShoppingPage.models.ShoppingAutocompleteData

class ShoppingAutocompleteAdapter(private val datas : Array<ShoppingAutocompleteData>): RecyclerView.Adapter<ShoppingAutocompleteAdapter.ViewHolder>(){
    inner class ViewHolder(private val viewBinding : RecyclerAutocompleteBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: ShoppingAutocompleteData){
            val profile = viewBinding.recyclerAutocompleteProfile
            val nick = viewBinding.recyclerAutocompleteNick
            val name = viewBinding.recyclerAutocompleteName

            nick.text = item.nick
            name.text = item.name
            Glide.with(itemView)
                .load(item.profile)
                .into(profile)


            viewBinding.recyclerAutocompleteLayout.setOnClickListener {

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