package com.instagram.src.main.home

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentHomeBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.home.adapter.PostAdapter
import com.instagram.src.main.home.adapter.StoryThumbnailAdapter
import com.instagram.src.main.home.models.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),HomeFragmentInterface{


    private var dataCount = 0
    private var nextpage = false
    private var page  = 0
    private val datas = arrayListOf<PostdetialData>()

    inner class getcontext{
        val fragcontext = context
        fun gotoComment(postid : Int){
            movetoCommentPage(postid)
        }
        fun gotoLikelist(postid : Int){
            movetoLikelistPage(postid)
        }
        fun gotoOthersprofile(targetNickname: String?){
            movetoOthersprofilePage(targetNickname)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("ddddd","onStart")


        HomeService(this).tryGetHomePostData(Jwt.getjwt(),page.toString())

        recyclerStoryThumbnail()


        // 최하단 스크롤 감지시, getMoreData 실행
        binding.homeScroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if(!v.canScrollVertically(1)){
                Log.d("aaaaa","lastScroll")

                getMoreData()
            }
        }

    }

    private fun recyclerStoryThumbnail(){

        val data = StoryThumbnailData(profile = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", nickName = "Noah", state = false)
        val data2 = StoryThumbnailData(profile = "https://drive.google.com/uc?export=view&id=1eP9m9FNrJS2FuRp5euySNIglCmvnzZtp", nickName = "Noah", state = true)
        val datas = arrayListOf<StoryThumbnailData>(data, data2, data, data2,data,data2,data,data2)


        val linking = getcontext()

        val adapter = StoryThumbnailAdapter(datas, linking)
        binding.recyclerHomeStory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerHomeStory.adapter = adapter
    }

    private fun recyclerPost(datas : ArrayList<PostdetialData>){

        if(nextpage){
            binding.recyclerHomeBody.adapter?.notifyItemInserted(datas.size)
            binding.recyclerHomeBody.adapter?.notifyDataSetChanged()
        }

        val linking = getcontext()

        val adapter = PostAdapter(datas, linking)
        binding.recyclerHomeBody.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerHomeBody.adapter = adapter


    }

    
    override fun onGetHomePostDataSuccess(response: PostData) {
        if(response.isSuccess){
            dataCount = response.result.postList.size
            page = response.result.page
            for(i in response.result.postList){
                datas.add(i)
            }

            recyclerPost(datas)
        }
    }

    override fun onGetHomePostDataFailure(message: String) {}

    
    fun getMoreData(){
        nextpage = true
        page++
        
        // page수를 증가시켜서 서버 요청
        HomeService(this).tryGetHomePostData(Jwt.getjwt(),page.toString())
    }

    fun movetoCommentPage(postid : Int){
        val Activity = activity as MainActivity
        setFragmentResult("fromHome", bundleOf("bundleKey" to postid))
        Activity.changeFragment("Comment")
    }

    fun movetoLikelistPage(postid : Int){
        val Activity = activity as MainActivity
        setFragmentResult("fromHome", bundleOf("bundleKey" to postid))
        Activity.changeFragment("Likelist")
    }

    fun movetoOthersprofilePage(targetNickname : String?){
        val Activity = activity as MainActivity
        setFragmentResult("fromHome", bundleOf("bundleKey" to targetNickname))
        Activity.changeFragment("ProfileOthers")
    }



    override fun onGetLikelistSuccess(response: LikelistData) {}
    override fun onGetLikelistFailure(message: String) { }




}