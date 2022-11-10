package com.instagram.src.main.home.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.databinding.RecyclerCommentBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.ProfilePage.ProfileRetrofitInterface
import com.instagram.src.main.ProfilePage.models.PostFollowingData
import com.instagram.src.main.home.API.CommentAPI
import com.instagram.src.main.home.CommentFragment
import com.instagram.src.main.home.LikelistFragment
import com.instagram.src.main.home.models.AddCocommentData
import com.instagram.src.main.home.models.CommentdetailData
import com.instagram.src.main.home.models.PostlikeData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommentAdapter(private val datas: ArrayList<CommentdetailData>, var linking : CommentFragment.getcontext? = null) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    inner class ViewHolder(private val viewBinding: RecyclerCommentBinding) : RecyclerView.ViewHolder(viewBinding.root){

        fun bind(item:CommentdetailData){
            val commentNum = item.commentNum
            val nickname = viewBinding.recyclerCommentNick
            val content = viewBinding.recyclerCommentText
            val profileimg = viewBinding.recyclerCommentProfile
            val time = viewBinding.recyclerCommentTime
            val layout = viewBinding.recyclerCommentLayout
            val commentlikebtn = viewBinding.commentLikebtn
            val commentlikecount = viewBinding.recyclerCommentLikecount
            var likestate = item.myLike

            Glide.with(itemView)
                .load(item.userImg)
                .into(profileimg)
            time.text = item.time
            content.text = item.content
            nickname.text = item.nickname

            if(commentNum == -1){
                val param = layout.layoutParams  as ViewGroup.MarginLayoutParams
                param.setMargins(200,50,50,0)
                layout.layoutParams =param
            }

            profileimg.setOnClickListener {
                linking?.gotoLiketoOthersprofile(item.nickname, item.userId)
            }
            nickname.setOnClickListener {
                linking?.gotoLiketoOthersprofile(item.nickname, item.userId)
            }

            // 스토리 여부
            if(item.storyExist == "Activate"){
                profileimg.setBackgroundResource(R.drawable.shape_story_unread)
            }else{
                profileimg.setBackgroundResource(R.drawable.shape_story)
            }
            
            // 좋아요 개수
            if(item.likeCount == 0){
                commentlikecount.isVisible = false
            }else{
                commentlikecount.text = "좋아요 ${item.likeCount}개"
            }

            //나의 좋아요 여부

            if(item.myLike == 1){
                Glide.with(itemView)
                    .load(R.drawable.home_like)
                    .into(commentlikebtn)
            }else{
                Glide.with(itemView)
                    .load(R.drawable.home_unlike)
                    .into(commentlikebtn)
            }


            // 댓글 좋아요, 좋아요취소 API 통신

            commentlikebtn.setOnClickListener {
                if(likestate == 1){
                    val buildLikeRetro = Retrofit.Builder()
                        .baseUrl("https://prod.lukechoi.shop/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    val api = buildLikeRetro.create(CommentAPI::class.java)
                    api.patchCommentunlike(Jwt.getjwt(),commentId = item.commentId)
                        .enqueue(object: Callback<PostlikeData> {
                            override fun onResponse(
                                call: Call<PostlikeData>,
                                response: Response<PostlikeData>
                            ) {
                                Log.d("API결과","${response.body().toString()}")
                                Log.d("API결과","${response.raw()}")
                                Glide.with(itemView)
                                    .load(R.drawable.home_unlike)
                                    .into(commentlikebtn)
                                likestate = 0
                            }
                            override fun onFailure(call: Call<PostlikeData>, t: Throwable) {
                                Log.d("API결과", "실패 : $t")
                            }
                        })
                }else{
                    val buildLikeRetro = Retrofit.Builder()
                        .baseUrl("https://prod.lukechoi.shop/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    val api = buildLikeRetro.create(CommentAPI::class.java)
                    api.postCommentlike(Jwt.getjwt(), commentId = item.commentId)
                        .enqueue(object: Callback<PostlikeData> {
                            override fun onResponse(
                                call: Call<PostlikeData>,
                                response: Response<PostlikeData>
                            ) {
                                Log.d("API결과","${response.body().toString()}")
                                Log.d("API결과","${response.raw()}")
                                Glide.with(itemView)
                                    .load(R.drawable.home_like)
                                    .into(commentlikebtn)
                                likestate = 1

                            }
                            override fun onFailure(call: Call<PostlikeData>, t: Throwable) {
                                Log.d("API결과", "실패 : $t")
                            }
                        })
                }

            }

            viewBinding.recyclerCommentAddcommentbtn.setOnClickListener {

                linking?.startCocoment(item.parentId)
            }






        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}