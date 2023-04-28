package com.kelvinhin.itunessearch.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kelvinhin.itunessearch.data.SearchRequest
import com.kelvinhin.itunessearch.data.SearchResult
import com.kelvinhin.itunessearch.repository.SearchRepository

class SearchViewModel : ViewModel() {
    private val searchRepository = SearchRepository()

    fun doSearch(request: SearchRequest): LiveData<SearchResult> {
        return searchRepository.doSearch(request)
    }
}