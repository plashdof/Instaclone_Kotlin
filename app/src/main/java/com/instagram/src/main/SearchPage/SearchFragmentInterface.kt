package com.instagram.src.main.SearchPage

import com.instagram.src.main.SearchPage.models.SearchAutocompleteData
import com.instagram.src.main.SearchPage.models.SearchThumbnailData

interface SearchFragmentInterface {

    fun onGetSearchThumbnailSuccess(response : SearchThumbnailData)
    fun onGetSearchThumbnailFailure(message : String)

    fun onGetSearchAutocompleteSuccess(response :SearchAutocompleteData)
    fun onGetSearchAutocompleteFailure(message :String)
}