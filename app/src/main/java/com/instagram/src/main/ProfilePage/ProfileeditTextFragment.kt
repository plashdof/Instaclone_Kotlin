package com.instagram.src.main.ProfilePage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentProfileeditTextBinding
import com.instagram.src.main.MainActivity

class ProfileeditTextFragment : BaseFragment<FragmentProfileeditTextBinding>(FragmentProfileeditTextBinding::bind, R.layout.fragment_profileedit_text){

    var pageName : String? = ""
    var editdata : String? = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cancel = binding.btnProfileeditTextCancel
        val store = binding.btnProfileeditTextStore
        val header = binding.tvProfileedittextHeader
        val title =binding.tvProfileedittextTitle
        val edit = binding.etProfileeditText


        edit.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                editdata = edit.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        // 변경할 항목 전달받음.
        setFragmentResultListener("headerText"){requestKey, bundle ->
            val data = bundle.getStringArray("bundleKey")
            pageName = data?.get(0)
            editdata =data?.get(1)

            header.text = pageName
            title.text = pageName
            edit.setText(editdata)
        }


        cancel.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.changeFragment("ProfileEdit")
        }
        
        // 입력한 항목, 입력한 내용 전달
        store.setOnClickListener {
            val data = arrayListOf(pageName,editdata)
            val Activity = activity as MainActivity
            setFragmentResult("changeText", bundleOf("bundleKey" to data))
            Activity.changeFragment("ProfileEdit")
        }

    }
}