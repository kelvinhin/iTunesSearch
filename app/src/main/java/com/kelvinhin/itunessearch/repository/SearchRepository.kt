package com.kelvinhin.itunessearch.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kelvinhin.itunessearch.api.ITunesApi
import com.kelvinhin.itunessearch.constants.Constants
import com.kelvinhin.itunessearch.constants.Url
import com.kelvinhin.itunessearch.data.SearchRequest
import com.kelvinhin.itunessearch.data.SearchResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchRepository {
    private val searchScope = CoroutineScope(Dispatchers.Main + Job())

    private val iTunesApi = Retrofit.Builder()
        .baseUrl(Url.BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ITunesApi::class.java)

    fun doSearch(request: SearchRequest): LiveData<SearchResult> {
        val data = MutableLiveData<SearchResult>()
        searchScope.launch {
            try {
                val result = iTunesApi.doSearch(
                    request.term,
                    request.country,
                    request.media,
                    request.entity,
                    request.offset,
                    request.limit
                )
                data.value = result
            } catch (e: Exception) {
                Log.e(Constants.LOG_TAG, "Do Search error: " + e.message)
            }
        }
        return data
    }
}