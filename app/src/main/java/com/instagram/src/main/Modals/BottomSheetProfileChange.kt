package com.instagram.src.main.Modals

import android.widget.TextView
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.instagram.R

class BottomSheetProfileChange() : BottomSheetDialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.bottom_sheet_profilechange, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.bottomsheet_newimg)?.setOnClickListener {
            Log.d("aaaaa", "clicked newimg")
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