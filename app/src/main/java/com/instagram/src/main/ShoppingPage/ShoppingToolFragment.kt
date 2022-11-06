package com.instagram.src.main.ShoppingPage

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentShoppingToolBinding
import com.instagram.src.main.MainActivity
import com.instagram.src.main.SearchPage.SearchAutocompleteFragment
import com.instagram.src.main.SearchPage.SearchRecentsearchFragment

class ShoppingToolFragment : BaseFragment<FragmentShoppingToolBinding>(FragmentShoppingToolBinding::bind, R.layout.fragment_shopping_tool){

    var shoppingtext : String? = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fragment 이동하자마자 edittext 에 포커싱
        binding.etShopping.requestFocus()

        // fragment 이동하자마자 키보드 올리기
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.etShopping,0)

        parentFragmentManager.beginTransaction()
            .replace(R.id.shopping_frm, ShoppingAutocompleteFragment(shoppingtext))
            .commit()

//        parentFragmentManager.beginTransaction()
//            .replace(R.id.shopping_frm, ShoppingRecentFragment())
//            .commit()

        binding.btnShoppingBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("Shopping")
        }

//        binding.etShopping.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                shoppingtext = binding.etShopping.text.toString()
//                if(shoppingtext!!.isNotBlank()){
//                    parentFragmentManager.beginTransaction()
//                        .replace(R.id.shopping_frm, ShoppingAutocompleteFragment(shoppingtext))
//                        .addToBackStack(null)
//                        .commit()
//                }else{
//                    parentFragmentManager.beginTransaction()
//                        .replace(R.id.shopping_frm, ShoppingRecentFragment())
//                        .addToBackStack(null)
//                        .commit()
//                }
//            }
//            override fun afterTextChanged(p0: Editable?) {}
//        })


    }


}