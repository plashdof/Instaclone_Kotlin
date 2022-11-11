package com.instagram.src.main.Modals

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.TextView
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.BottomSheetProfilechangeBinding
import com.instagram.src.main.MainActivity
import com.instagram.src.main.ProfilePage.ProfileeditFragment
import java.text.SimpleDateFormat

class BottomSheetProfileChange() : BottomSheetDialogFragment(){


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d("aaaaaaaaaa","onCreateView")
        return inflater.inflate(R.layout.bottom_sheet_profilechange, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("aaaaaaaaaa","onViewCreated")



        view.findViewById<TextView>(R.id.bottomsheet_newimg)?.setOnClickListener {

        }

        view.findViewById<TextView>(R.id.bottomsheet_toFacebook)?.setOnClickListener {
            Log.d("aaaaa", "clicked tofacebook")
        }

        view.findViewById<TextView>(R.id.bottomsheet_avatar)?.setOnClickListener {
            Log.d("aaaaa", "clicked avator")
        }

        view.findViewById<TextView>(R.id.bottomsheet_delete)?.setOnClickListener {
            Log.d("aaaaa", "clicked delete")
        }

    }





}