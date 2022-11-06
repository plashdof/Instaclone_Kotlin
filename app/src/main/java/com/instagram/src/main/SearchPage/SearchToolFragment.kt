package com.instagram.src.main.SearchPage


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentSearchToolBinding
import com.instagram.src.main.MainActivity

class SearchToolFragment : BaseFragment<FragmentSearchToolBinding>(FragmentSearchToolBinding::bind, R.layout.fragment_search_tool){

    var searchtext : String? = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fragment 이동하자마자 edittext 에 포커싱
        binding.etSearchtool.requestFocus()

        // fragment 이동하자마자 키보드 올리기
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.etSearchtool,0)

        parentFragmentManager.beginTransaction()
            .replace(R.id.search_frm, SearchRecentsearchFragment())
            .commit()

        binding.btnSearchtoolBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("Search")
        }

        binding.etSearchtool.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchtext = binding.etSearchtool.text.toString()
                if(searchtext!!.isNotBlank()){
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.search_frm, SearchAutocompleteFragment(searchtext))
                        .addToBackStack(null)
                        .commit()
                }else{
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.search_frm, SearchRecentsearchFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })


    }


}