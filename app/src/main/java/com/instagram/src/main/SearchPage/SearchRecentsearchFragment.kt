package com.instagram.src.main.SearchPage

import android.os.Bundle
import android.view.View
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentSearchRecentsearchBinding
import com.instagram.src.main.MainActivity

class SearchRecentsearchFragment : BaseFragment<FragmentSearchRecentsearchBinding>(FragmentSearchRecentsearchBinding::bind, R.layout.fragment_search_recentsearch){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRecentsearchBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("Search")
        }
    }
}