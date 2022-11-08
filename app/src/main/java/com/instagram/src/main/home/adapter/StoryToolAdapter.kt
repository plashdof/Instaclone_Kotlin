package com.instagram.src.main.home.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.instagram.databinding.ViewpageStoryBinding
import com.instagram.src.main.home.StoryToolActivity
import com.instagram.src.main.home.models.StoryData
import com.instagram.src.main.home.models.StorythumbnailData
import jp.shts.android.storiesprogressview.StoriesProgressView

class StoryToolAdapter(private val datas : ArrayList<StorythumbnailData>, var linking : StoryToolActivity.getcontext) : RecyclerView.Adapter<StoryToolAdapter.ViewHolder>(){



    inner class ViewHolder(private val viewBinding: ViewpageStoryBinding) : StoriesProgressView.StoriesListener, RecyclerView.ViewHolder(viewBinding.root){

        private lateinit var items : StorythumbnailData
        private lateinit var progressbar : StoriesProgressView
        var currentindex = 0
        var clicked = false

        fun bind(item : StorythumbnailData){

            items = item

            val profile= viewBinding.storyProfile
            val nick = viewBinding.tvStoryNick
            val time = viewBinding.tvStoryTime
            val storyimg = viewBinding.storyImg

            val leftside = viewBinding.storyLeftside
            val rightside = viewBinding.storyRightside
            val maxindex = item.storyDataList.size - 1

            Glide.with(itemView)
                .load(item.profileUrl)
                .into(profile)

            nick.text = item.nickname
            time.text = item.storyDataList[0].createdAt.toString()

            // 처음 이미지
            Glide.with(itemView)
                .load(item.storyDataList[0].imgUrl)
                .into(storyimg)


            // 스토리 progressbar
            progressbar = viewBinding.storyProgressbar
            progressbar.setStoriesCount(item.storyDataList.size)
            progressbar.setStoryDuration(4000L)
            progressbar.setStoriesListener(this@ViewHolder)
            progressbar.startStories()


            leftside.setOnClickListener {

                if(currentindex != 0){
                    --currentindex
                    Glide.with(itemView)
                        .load(item.storyDataList[currentindex].imgUrl)
                        .into(storyimg)
                    time.text = item.storyDataList[currentindex].createdAt.toString()
                    progressbar.reverse()

                }

            }

            rightside.setOnClickListener {

                clicked = true

                if(currentindex != maxindex){
                    ++currentindex
                    Glide.with(itemView)
                        .load(item.storyDataList[currentindex].imgUrl)
                        .into(storyimg)
                    time.text = item.storyDataList[currentindex].createdAt.toString()
                    progressbar.skip()
                }

            }

        }

        override fun onNext() {
            Log.d("aaaa", "$currentindex")

            if(currentindex != items.storyDataList.size - 1 && !clicked){
                ++currentindex
                Glide.with(itemView)
                    .load(items.storyDataList[currentindex].imgUrl)
                    .into(viewBinding.storyImg)
                viewBinding.tvStoryTime.text = items.storyDataList[currentindex].createdAt.toString()
            }

            clicked = false
        }

        override fun onPrev() {
            Log.d("aaaa", "$currentindex")
        }

        override fun onComplete() {
            //TODO complete 되면 다음 View로 넘어가게하기

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ViewpageStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size



}