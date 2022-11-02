package com.instagram.src.main.ProfilePage

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.instagram.config.BaseActivity
import com.instagram.databinding.ActivityProfileeditTextBinding

class ProfileeditTextActivity : BaseActivity<ActivityProfileeditTextBinding>(ActivityProfileeditTextBinding::inflate){
    var pageName : String? = ""
    var editdata : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        pageName = intent.getStringExtra("title")
        editdata = intent.getStringExtra("data")

        header.text = pageName
        title.text = pageName
        edit.setText(editdata)


        cancel.setOnClickListener {
            val intent = Intent(this,ProfileeditActivity::class.java)
            startActivity(intent)
        }

        // 입력한 항목, 입력한 내용 전달
        store.setOnClickListener {
            val intent = Intent(this, ProfileeditActivity::class.java)
                .putExtra("pageName",pageName)
                .putExtra("editData", editdata)
                .putExtra("flag","true")
            startActivity(intent)
        }

    }
}
