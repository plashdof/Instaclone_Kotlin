package com.instagram.src.main.SearchPage

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentSearchBinding
import com.instagram.src.main.MainActivity
import com.instagram.src.main.ProfilePage.adapter.ProfileThumbnailAdapter
import com.instagram.src.main.SearchPage.adapter.SearchThumbnailAdapter
import com.instagram.src.main.SearchPage.models.SearchThumbnailData

class SearchFragment : BaseFragment<FragmentSearchBinding> (FragmentSearchBinding::bind, R.layout.fragment_search){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("SearchTool")
        }

        recyclerSearchThumbnail()
    }

    fun recyclerSearchThumbnail(){
        val data = SearchThumbnailData(img = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", id = "12")
        val datas = arrayOf(data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data
            , data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data)
        val adapter = SearchThumbnailAdapter(datas)
        binding.recyclerSearchThumbnail.layoutManager = GridLayoutManager(context,3,
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerSearchThumbnail.adapter = adapter
    }



}