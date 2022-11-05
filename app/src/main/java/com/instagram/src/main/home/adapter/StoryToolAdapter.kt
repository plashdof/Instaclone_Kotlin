package com.instagram.src.main.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.instagram.databinding.ViewpageStoryBinding
import com.instagram.src.main.home.StoryToolActivity
import com.instagram.src.main.home.models.StoryData
import jp.shts.android.storiesprogressview.StoriesProgressView

class StoryToolAdapter(private val datas : ArrayList<StoryData>, var linking : StoryToolActivity.getcontext) : RecyclerView.Adapter<StoryToolAdapter.ViewHolder>(){



    inner class ViewHolder(private val viewBinding: ViewpageStoryBinding) : StoriesProgressView.StoriesListener, RecyclerView.ViewHolder(viewBinding.root){

        private lateinit var items : StoryData
        private lateinit var progressbar : StoriesProgressView
        var currentindex = 0
        var clicked = false

        fun bind(item : StoryData){

            items = item

            val profile= viewBinding.storyProfile
            val nick = viewBinding.tvStoryNick
            val time = viewBinding.tvStoryTime
            val storyimg = viewBinding.storyImg

            val leftside = viewBinding.storyLeftside
            val rightside = viewBinding.storyRightside
            val maxindex = item.imgData.size - 1

            Glide.with(itemView)
                .load(item.profile)
                .into(profile)

            nick.text = item.nick
            time.text = item.imgData[0].time

            // 처음 이미지
            Glide.with(itemView)
                .load(item.imgData[0].img)
                .into(storyimg)


            // 스토리 progressbar
            progressbar = viewBinding.storyProgressbar
            progressbar.setStoriesCount(item.imgData.size)
            progressbar.setStoryDuration(4000L)
            progressbar.setStoriesListener(this@ViewHolder)
            progressbar.startStories()


            leftside.setOnClickListener {

                if(currentindex != 0){
                    --currentindex
                    Glide.with(itemView)
                        .load(item.imgData[currentindex].img)
                        .into(storyimg)
                    time.text = item.imgData[currentindex].time
                    progressbar.reverse()

                }

            }

            rightside.setOnClickListener {

                clicked = true

                if(currentindex != maxindex){
                    ++currentindex
                    Glide.with(itemView)
                        .load(item.imgData[currentindex].img)
                        .into(storyimg)
                    time.text = item.imgData[currentindex].time
                    progressbar.skip()
                }

            }

        }

        override fun onNext() {
            Log.d("aaaa", "$currentindex")

            if(currentindex != items.imgData.size - 1 && !clicked){
                ++currentindex
                Glide.with(itemView)
                    .load(items.imgData[currentindex].img)
                    .into(viewBinding.storyImg)
                viewBinding.tvStoryTime.text = items.imgData[currentindex].time
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