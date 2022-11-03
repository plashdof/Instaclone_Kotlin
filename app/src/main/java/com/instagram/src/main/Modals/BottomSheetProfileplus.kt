package com.instagram.src.main.Modals

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.instagram.R

class BottomSheetProfileplus() : BottomSheetDialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.bottom_sheet_profileplus, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<LinearLayout>(R.id.bottomsheet_post)?.setOnClickListener {
            Log.d("aaaaa", "clicked post")
        }

        view.findViewById<LinearLayout>(R.id.bottomsheet_reels)?.setOnClickListener {
            Log.d("aaaaa", "clicked reels")
        }

        view.findViewById<LinearLayout>(R.id.bottomsheet_story)?.setOnClickListener {
            Log.d("aaaaa", "clicked story")
        }

        view.findViewById<LinearLayout>(R.id.bottomsheet_highlight)?.setOnClickListener {
            Log.d("aaaaa", "clicked highlight")
        }

        view.findViewById<LinearLayout>(R.id.bottomsheet_live)?.setOnClickListener {
            Log.d("aaaaa", "clicked live")
        }

        view.findViewById<LinearLayout>(R.id.bottomsheet_guide)?.setOnClickListener {
            Log.d("aaaaa", "clicked guide")
        }
    }



}