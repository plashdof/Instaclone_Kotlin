package com.instagram.src.main.ShoppingPage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentSearchAutocompleteBinding
import com.instagram.databinding.FragmentShoppingAutocompleteBinding
import com.instagram.src.main.SearchPage.adapter.SearchAutocompleteAdapter
import com.instagram.src.main.SearchPage.models.SearchAutocompleteData

class ShoppingAutocompleteFragment(var editdata : String?) : BaseFragment<FragmentShoppingAutocompleteBinding>(
    FragmentShoppingAutocompleteBinding::bind, R.layout.fragment_shopping_autocomplete) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAutocomplete()

    }

    fun recyclerAutocomplete(){
        val data = SearchAutocompleteData(name = "진성", nick = "noah", profile = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp")
        val data2 = SearchAutocompleteData(name = "진성", nick = "noah", profile = "https://source.unsplash.com/collection/2463607/1080x1080")
        val datas = arrayOf(data,data2,data,data,data2,data,data2,data,data2,data,data2,data,data,data,)

        val adapter = SearchAutocompleteAdapter(datas)
        binding.recyclerShoppingAutocomplete.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.recyclerShoppingAutocomplete.adapter = adapter


    }

}