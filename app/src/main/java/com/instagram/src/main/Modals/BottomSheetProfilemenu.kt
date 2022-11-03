package com.instagram.src.main.Modals

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.instagram.R

class BottomSheetProfilemenu() : BottomSheetDialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.bottom_sheet_profilemenu, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<LinearLayout>(R.id.bottomsheet_setting)?.setOnClickListener {
            Log.d("aaaaa", "clicked setting")
        }

        view.findViewById<LinearLayout>(R.id.bottomsheet_store)?.setOnClickListener {
            Log.d("aaaaa", "clicked store")
        }

        view.findViewById<LinearLayout>(R.id.bottomsheet_myactive)?.setOnClickListener {
            Log.d("aaaaa", "clicked myactive")
        }

        view.findViewById<LinearLayout>(R.id.bottomsheet_qr)?.setOnClickListener {
            Log.d("aaaaa", "clicked qr")
        }

        view.findViewById<LinearLayout>(R.id.bottomsheet_tag)?.setOnClickListener {
            Log.d("aaaaa", "clicked tag")
        }

        view.findViewById<LinearLayout>(R.id.bottomsheet_friends)?.setOnClickListener {
            Log.d("aaaaa", "clicked friends")
        }

        view.findViewById<LinearLayout>(R.id.bottomsheet_corona)?.setOnClickListener {
            Log.d("aaaaa", "clicked corona")
        }
    }



}