package com.instagram.src.main.ShoppingPage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentShoppingBinding
import com.instagram.src.main.MainActivity
import com.instagram.src.main.ShoppingPage.adapter.ShoppingThumbnailAdapter
import com.instagram.src.main.ShoppingPage.models.ShoppingThumbnailData

class ShoppingFragment : BaseFragment<FragmentShoppingBinding>(FragmentShoppingBinding::bind, R.layout.fragment_shopping) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etShopping.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("ShoppingTool")
        }
        recycler()
    }

    fun recycler(){

        val data = ShoppingThumbnailData(img = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", id="1")
        val dat2 = ShoppingThumbnailData(img = "https://source.unsplash.com/collection/3730086/1080x1080", id="1")
        val dat3 = ShoppingThumbnailData(img = "https://source.unsplash.com/collection/2463607/1080x1080", id="1")

        val datas = arrayListOf(data, dat2, dat3,data, dat2, dat3,data, dat2, dat3,data, dat2, dat3,data, dat2, dat3,
            data, dat2, dat3,data, dat2, dat3,data, dat2, dat3,)

        val adapter = ShoppingThumbnailAdapter(datas)
        binding.recyclerShopping.layoutManager = GridLayoutManager(context,2, LinearLayoutManager.VERTICAL,false)
        binding.recyclerShopping.adapter = adapter
    }
}