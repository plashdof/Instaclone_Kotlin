package com.instagram.src.main.SearchPage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.instagram.R
import com.instagram.config.BaseFragment
import com.instagram.databinding.FragmentSearchAutocompleteBinding
import com.instagram.src.main.Jwt
import com.instagram.src.main.MainActivity
import com.instagram.src.main.SearchPage.adapter.SearchAutocompleteAdapter
import com.instagram.src.main.SearchPage.adapter.SearchThumbnailAdapter
import com.instagram.src.main.SearchPage.models.Autocompletelist
import com.instagram.src.main.SearchPage.models.SearchAutocompleteData
import com.instagram.src.main.SearchPage.models.SearchThumbnailData

class SearchAutocompleteFragment(private var data: String?) : BaseFragment<FragmentSearchAutocompleteBinding>(FragmentSearchAutocompleteBinding::bind, R.layout.fragment_search_autocomplete),SearchFragmentInterface {


    inner class getcontext{
        val fragcontext = context

        fun gotoLiketoOthersprofile(targetNickname: String?, userId:Int){
            moveLiketoOthersprofilePage(targetNickname, userId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(data?.isNotBlank() == true){
            binding.recyclerAutocompleteEdittext.text = data
            startAutocomplete()
        }

    }

    fun startAutocomplete(){
        SearchService(this).tryGetSearchAutoComplete(Jwt.getjwt(),data,0)
    }

    override fun onGetSearchAutocompleteSuccess(response: SearchAutocompleteData) {
        if(response.isSuccess){
            recyclerAutocomplete(response.result)
        }
    }

    override fun onGetSearchAutocompleteFailure(message: String) {
        TODO("Not yet implemented")
    }

    fun recyclerAutocomplete(datas : ArrayList<Autocompletelist>){

        val linking = getcontext()
        val adapter = SearchAutocompleteAdapter(datas,linking)
        binding.recyclerSearchAutocomplete.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.recyclerSearchAutocomplete.adapter = adapter


    }

    fun moveLiketoOthersprofilePage(targetNickname : String?, userId: Int){

        Log.d("aaaaa","moveLiketoOthers $targetNickname   $userId")

        val data = arrayOf(targetNickname, userId.toString())
        val Activity = activity as MainActivity
        setFragmentResult("fromLikelist", bundleOf("bundleKey" to data))
        Activity.changeFragment("ProfileOthers")
    }

    override fun onGetSearchThumbnailSuccess(response: SearchThumbnailData) {}
    override fun onGetSearchThumbnailFailure(message: String) {}

}