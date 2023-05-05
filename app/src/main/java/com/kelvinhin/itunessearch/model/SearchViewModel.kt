package com.kelvinhin.itunessearch.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvinhin.itunessearch.R
import com.kelvinhin.itunessearch.constants.Constants
import com.kelvinhin.itunessearch.constants.MediaEntities
import com.kelvinhin.itunessearch.data.SearchRequest
import com.kelvinhin.itunessearch.data.SearchResult
import com.kelvinhin.itunessearch.repository.SearchRepository
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, SUCCESS, ERROR }

class SearchViewModel : ViewModel() {
    private val _request = MutableLiveData<SearchRequest>()
    val request: LiveData<SearchRequest> = _request

    private val _response = MutableLiveData<SearchResult>()
    val response: LiveData<SearchResult> = _response

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val country = MutableLiveData<String>()

    fun getCountry(): MutableLiveData<String> {
        return country
    }

    fun setCountry(country: String) {
        request.value?.country = country
        this.country.postValue(country)
    }

    fun constructRequest (keyword: String, selectedEntityId: Int) {
        _request.value = SearchRequest(
            term = keyword,
            country = getCountry().value ?: Constants.DEFAULT_LOCALE,
            entity = getSelectedEntity(selectedEntityId)
        )
    }

    fun doSearch() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                request.value?.let {
                    val result = SearchRepository.iTunesApi.doSearch(
                        it.term,
                        it.country,
                        it.media,
                        it.entity,
                        it.offset,
                        it.limit
                    )
                    _response.value = result
                    _status.value = ApiStatus.SUCCESS
                }
            } catch (e: Exception) {
                Log.e(Constants.LOG_TAG, "Do Search error: " + e.message)
                _status.value = ApiStatus.ERROR
            }
        }
    }

    fun getSelectedEntity (selectedEntityId: Int): String? {
        return when (selectedEntityId) {
            R.id.chip_song -> MediaEntities.SONG
            R.id.chip_album -> MediaEntities.ALBUM
            R.id.chip_mv -> MediaEntities.MUSIC_VIDEO
            else -> null
        }
    }
}