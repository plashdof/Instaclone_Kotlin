package com.instagram.src.main.SearchPage


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentSearchRecentsearchBinding
import com.instagram.src.main.SearchPage.adapter.SearchRecentAdapter

class SearchRecentsearchFragment : BaseFragment<FragmentSearchRecentsearchBinding>(FragmentSearchRecentsearchBinding::bind, R.layout.fragment_search_recentsearch){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerRecent()
    }


    fun recyclerRecent(){

        val datas = arrayOf("noah", "no", "eric"," asss","ff","라이징","컴공")

        val adapter = SearchRecentAdapter(datas)
        binding.recyclerSearchRecentsearch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.recyclerSearchRecentsearch.adapter = adapter
    }
}