package com.instagram.src.main.SearchPage

import com.instagram.src.main.SearchPage.models.SearchAutocompleteData
import com.instagram.src.main.SearchPage.models.SearchThumbnailData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchRetrofitInterface {

    @GET("/searchs/getList")
    fun getSearchThumbnail(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Query("cursor") cursor : Int,
    ) : Call<SearchThumbnailData>

    @GET("/searchs/autoSearch")
    fun getAutoSearch(
        @Header("ACCESS-TOKEN") jwt : String? = "",
        @Query("searchKey") searchKey : String?="",
        @Query("cursor") cursor : Int,
    ) : Call<SearchAutocompleteData>
}