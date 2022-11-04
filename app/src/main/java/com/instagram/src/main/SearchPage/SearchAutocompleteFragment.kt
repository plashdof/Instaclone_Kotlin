package com.instagram.src.main.SearchPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentSearchAutocompleteBinding
import com.instagram.src.main.SearchPage.adapter.SearchAutocompleteAdapter
import com.instagram.src.main.SearchPage.models.SearchAutocompleteData

class SearchAutocompleteFragment(private var data: String?) : BaseFragment<FragmentSearchAutocompleteBinding>(FragmentSearchAutocompleteBinding::bind, R.layout.fragment_search_autocomplete) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerAutocompleteEdittext.text = data

        recyclerAutocomplete()

    }

    fun recyclerAutocomplete(){
        val data = SearchAutocompleteData(name = "진성", nick = "noah", profile = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp")
        val datas = arrayOf(data,data,data,data,data,data,data,data,data,data,data,data,data,data,)

        val adapter = SearchAutocompleteAdapter(datas)
        binding.recyclerSearchAutocomplete.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.recyclerSearchAutocomplete.adapter = adapter


    }

}