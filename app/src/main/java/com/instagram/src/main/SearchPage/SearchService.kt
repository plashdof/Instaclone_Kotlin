package com.instagram.src.main.SearchPage

import com.instagram.config.ApplicationClass
import com.instagram.src.main.SearchPage.models.SearchAutocompleteData
import com.instagram.src.main.SearchPage.models.SearchThumbnailData
import com.instagram.src.main.home.HomeRetrofitInterface
import com.instagram.src.main.home.models.PostData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchService(val searchFragmentInterface: SearchFragmentInterface) {

    fun tryGetSearchThumbnail(jwt:String?, cursor:Int){
        val searchRetrofitInterface = ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java)
        searchRetrofitInterface.getSearchThumbnail(jwt,cursor).enqueue(object: Callback<SearchThumbnailData> {
            override fun onResponse(call: Call<SearchThumbnailData>, response: Response<SearchThumbnailData>) {
                searchFragmentInterface.onGetSearchThumbnailSuccess(response.body() as SearchThumbnailData)
            }

            override fun onFailure(call: Call<SearchThumbnailData>, t: Throwable) {
                searchFragmentInterface.onGetSearchThumbnailFailure(t.message ?:"통신오류")
            }


        })
    }

    fun tryGetSearchAutoComplete(jwt:String?, searchKey:String? = "", cursor:Int){
        val searchRetrofitInterface = ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java)
        searchRetrofitInterface.getAutoSearch(jwt,searchKey,cursor).enqueue(object: Callback<SearchAutocompleteData> {
            override fun onResponse(call: Call<SearchAutocompleteData>, response: Response<SearchAutocompleteData>) {
                searchFragmentInterface.onGetSearchAutocompleteSuccess(response.body() as SearchAutocompleteData)
            }

            override fun onFailure(call: Call<SearchAutocompleteData>, t: Throwable) {
                searchFragmentInterface.onGetSearchAutocompleteFailure(t.message ?:"통신오류")
            }


        })
    }
}