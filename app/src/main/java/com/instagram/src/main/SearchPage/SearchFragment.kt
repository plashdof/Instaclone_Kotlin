package com.instagram.src.main.SearchPage

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentSearchBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.ProfilePage.adapter.ProfileThumbnailAdapter
import com.instagram.src.main.SearchPage.adapter.SearchThumbnailAdapter
import com.instagram.src.main.SearchPage.models.SearchAutocompleteData
import com.instagram.src.main.SearchPage.models.SearchImgList
import com.instagram.src.main.SearchPage.models.SearchThumbnailData
import com.instagram.src.main.home.HomeService

class SearchFragment : BaseFragment<FragmentSearchBinding> (FragmentSearchBinding::bind, R.layout.fragment_search),SearchFragmentInterface{

    var cursor = 0
    var nextpage = false
    private var datas = arrayListOf<SearchImgList>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("SearchTool")
        }

        // 최하단 스크롤 감지시, getMoreData 실행
        binding.SearchScroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if(!v.canScrollVertically(1)){
                Log.d("aaaaa","lastScroll")

                getMoreData()
            }
        }


        SearchService(this).tryGetSearchThumbnail(Jwt.getjwt(),cursor)

    }

    override fun onGetSearchThumbnailSuccess(response: SearchThumbnailData) {
        for(i in response.result){
            datas.add(i)
        }
        recyclerSearchThumbnail(datas)
    }

    override fun onGetSearchThumbnailFailure(message: String) {
        TODO("Not yet implemented")
    }

    fun getMoreData(){
        nextpage = true
        cursor++
        // page수를 증가시켜서 서버 요청
        SearchService(this).tryGetSearchThumbnail(Jwt.getjwt(),cursor)
    }

    fun recyclerSearchThumbnail(datas : ArrayList<SearchImgList>){

        if(nextpage){
            binding.recyclerSearchThumbnail.adapter?.notifyItemInserted(datas.size)
            binding.recyclerSearchThumbnail.adapter?.notifyDataSetChanged()
        }

        val adapter = SearchThumbnailAdapter(datas)
        binding.recyclerSearchThumbnail.layoutManager = GridLayoutManager(context,3,
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerSearchThumbnail.adapter = adapter
    }


    override fun onGetSearchAutocompleteSuccess(response: SearchAutocompleteData) {}
    override fun onGetSearchAutocompleteFailure(message: String) {}





}