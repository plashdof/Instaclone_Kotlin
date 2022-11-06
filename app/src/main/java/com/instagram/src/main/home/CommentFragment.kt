package com.instagram.src.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentPostCommentBinding
import com.instagram.src.main.MainActivity

class CommentFragment : BaseFragment<FragmentPostCommentBinding>(FragmentPostCommentBinding::bind, R.layout.fragment_post_comment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("fromHome"){requestKey, bundle ->
            val result = bundle.getInt("bundleKey")
            showCustomToast("Post ID : $result")
        }

        binding.btnCommentBackbtn.setOnClickListener {
            val Activity = activity as MainActivity
            Activity.onBackPressed()
        }

    }
}