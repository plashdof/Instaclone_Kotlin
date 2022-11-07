package com.instagram.src.main.home.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.databinding.RecyclerLikelistBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.ProfilePage.ProfileRetrofitInterface
import com.instagram.src.main.ProfilePage.models.PostFollowingData
import com.instagram.src.main.home.API.HomelikeAPI
import com.instagram.src.main.home.HomeFragment
import com.instagram.src.main.home.LikelistFragment
import com.instagram.src.main.home.models.LikelistdetialData
import com.instagram.src.main.home.models.PostlikeData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LikelistAdapter(private val datas: ArrayList<LikelistdetialData>, var linking : LikelistFragment.getcontext? = null) : RecyclerView.Adapter<LikelistAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewBinding: RecyclerLikelistBinding) : RecyclerView.ViewHolder(viewBinding.root){

        fun bind(item:LikelistdetialData){
            val profileimg = viewBinding.recyclerLikelistProfileimg
            val nickname = viewBinding.recyclerLikelistNick
            val name = viewBinding.recyclerLikelistName
            val followbtn = viewBinding.recyclerLikelistFollowbtn
            val userId = item.userId

            Log.d("aaaaa","데이터들 ${item.userImg} ${item.nickname} ${item.name} ${item.followState} ${item.storyExit}")

            Glide.with(itemView)
                .load(item.userImg)
                .into(profileimg)
            nickname.text= item.nickname
            name.text = item.name

            viewBinding.recyclerLikelistBorder.isVisible = item.storyExit == "Y"

            // 내가 팔로우하는지 여부 체크해서 버튼 스타일 변경
            
            if(item.followState == "팔로잉"){
                followbtn.setBackgroundResource(R.drawable.shape_et)
                followbtn.setTextColor(Color.BLACK)
                followbtn.text = "팔로잉"
            }else if(item.followState == "본인"){
                followbtn.isVisible = false
            } else{
                followbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
                followbtn.setTextColor(Color.WHITE)
                followbtn.text = "팔로우"
            }
            
            // 프로필사진. 닉네임 클릭시 해당 유저 프로필페이지 이동

            profileimg.setOnClickListener {
                linking?.gotoOthersprofile(item.nickname)
            }
            nickname.setOnClickListener {
                Log.d("aaaaa","Clicked")
                linking?.gotoOthersprofile(item.nickname)
            }


            // 팔로우, 팔로우취소 버튼 클릭 API 통신

            followbtn.setOnClickListener {
                if(followbtn.text == "팔로잉"){
                    val buildLikeRetro = Retrofit.Builder()
                        .baseUrl("https://prod.lukechoi.shop/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    val api = buildLikeRetro.create(ProfileRetrofitInterface::class.java)
                    api.patchunFollow(Jwt.getjwt(),item.userId)
                        .enqueue(object: Callback<PostFollowingData> {
                            override fun onResponse(
                                call: Call<PostFollowingData>,
                                response: Response<PostFollowingData>
                            ) {
                                Log.d("API결과","${response.body()?.result}")
                                Log.d("API결과","${response.raw()}")
                                followbtn.setBackgroundResource(R.drawable.shape_loginbtn_active)
                                followbtn.setTextColor(Color.WHITE)
                                followbtn.text = "팔로우"
                            }
                            override fun onFailure(call: Call<PostFollowingData>, t: Throwable) {
                                Log.d("API결과", "실패 : $t")
                            }
                        })

                }else{
                    val buildLikeRetro = Retrofit.Builder()
                        .baseUrl("https://prod.lukechoi.shop/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    val api = buildLikeRetro.create(ProfileRetrofitInterface::class.java)
                    api.postFollows(Jwt.getjwt(),item.userId)
                        .enqueue(object: Callback<PostFollowingData> {
                            override fun onResponse(
                                call: Call<PostFollowingData>,
                                response: Response<PostFollowingData>
                            ) {
                                Log.d("API결과","${response.body()?.result}")
                                Log.d("API결과","${response.raw()}")
                                followbtn.setBackgroundResource(R.drawable.shape_et)
                                followbtn.setTextColor(Color.BLACK)
                                followbtn.text = "팔로잉"
                            }
                            override fun onFailure(call: Call<PostFollowingData>, t: Throwable) {
                                Log.d("API결과", "실패 : $t")
                            }
                        })
                }
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerLikelistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}