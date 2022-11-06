package com.instagram.src.main.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.instagram.databinding.RecyclerPostBinding
import com.instagram.src.main.home.models.PostdetialData


class PostAdapter(private val datas: ArrayList<PostdetialData>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {


    inner class ViewHolder(private val viewBinding: RecyclerPostBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: PostdetialData) {
            val nick = viewBinding.recyclerPostNick
            val profileimg = viewBinding.recylerPostProfileimg
            val likecount = viewBinding.recyclerPostBottomlikeNumber
            val postnick = viewBinding.recyclerPostBottomtextNick
            val firstcontent = viewBinding.recyclerPostBottomtextFirstline
            val hashtaglist = viewBinding.recyclerPostHashtag
            val commentCount = viewBinding.recyclerPostCommentCount
            val postTime = viewBinding.recyclerPostTime
            val detailLine1 = viewBinding.recyclerPostBottomDetailLine1Text
            val moreseebtn = viewBinding.recyclerPostBottomDetailLine1Moreseebtn
            val detailLine2 = viewBinding.recyclerPostBottomDetailLine2

            Log.d("aaaaa", "${item.imgUrlList[0]}")

            nick.text = item.nickname
            postnick.text = item.nickname
            postTime.text = item.time


            // Comment Parsing 로직

            var count = 0
            var flag = true
            val contentString = item.content.toString()

            for (i in 0 until contentString.length - 1) {
                if (contentString[i] == ' ') {
                    count++
                }

                if (count == 3) {
                    flag = false
                    firstcontent.text = contentString.substring(0, i + 1)

                    for (j in i + 1 until contentString.length - 1) {
                        if (contentString[j] == ' ') {
                            count++
                        }

                        if (count == 4) {
                            detailLine1.text = contentString.substring(i + 1, j + 1)
                            detailLine2.text = contentString.substring(j + 1)
                            break
                        }
                    }

                    detailLine2.isVisible = false
                    moreseebtn.setOnClickListener {
                        detailLine2.isVisible = true
                        moreseebtn.isVisible = false
                    }

                }
            }

            if (flag) {
                detailLine1.isVisible = false
                detailLine2.isVisible = false
                moreseebtn.isVisible = false
                firstcontent.text = contentString
            }


            // 나머지 데이터로 layout 채우기

            if (item.likeCount == 0) {
                likecount.isVisible = false
            } else {
                likecount.text = "좋아요 ${item.likeCount}개"
            }

            if (item.commentCount == 0) {
                commentCount.isVisible = false
            } else {
                commentCount.text = "댓글 ${item.commentCount}개 모두 보기"
            }

            if (item.hashTagList.isNullOrEmpty()) {
                hashtaglist.isVisible = false
            } else {
                var hashTagString = ""
                for (i in item.hashTagList) {
                    hashTagString += "#$i "
                }
                hashtaglist.text = hashTagString
            }


            Glide.with(itemView)
                .load(item.userImg)
                .into(profileimg)

            val viewpager = viewBinding.recyclerPostViewpager
            val viewImg = item.imgUrlList

            val indicator = viewBinding.recyclerPostIndicator
            indicator.noOfPages = viewImg.size

            val adapter = PostViewAdapter(viewImg)
            viewpager.adapter = adapter


            // viewpager 와 indicator 연결부분

            viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    indicator.onPageChange(position)
                    Log.d("aaaa", "selected position : $position")
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            RecyclerPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}