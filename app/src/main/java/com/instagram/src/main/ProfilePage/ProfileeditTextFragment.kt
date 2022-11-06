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
    var name : String? = ""
    var nick : String? = ""
    var website : String? = ""
    var description : String? = ""
    var profile : String? = ""

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


        // ProfileeditFragment 에서 변경할 항목 & 유지할 항목 전달받음.

        setFragmentResultListener("fromProfileeditFragment"){requestKey, bundle ->
            val result = bundle.getStringArray("bundleKey")

            if(result != null){
                pageName = result[0]
                editdata = result[1]

                name = result[2]
                nick = result[3]
                website = result[4]
                description = result[5]
                profile = result[6]
            }

            header.text = pageName
            title.text = pageName
            edit.setText(editdata)
        }


        cancel.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.onBackPressed()
        }

        // ProfileeditFragment 로 입력한 항목, 입력한 내용, 유지할 내용 전달
        
        store.setOnClickListener {
            val data = arrayOf(pageName,editdata, name, nick, website, description, profile)
            val Activity = activity as MainActivity
            setFragmentResult("fromProfileeditTextFragment", bundleOf("bundleKey" to data))
            Activity.changeFragment("ProfileEdit")
        }

    }
}

