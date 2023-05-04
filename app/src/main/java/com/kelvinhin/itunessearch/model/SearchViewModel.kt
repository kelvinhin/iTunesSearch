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

class SearchViewModel : ViewModel() {
    private val _response = MutableLiveData<SearchResult>()
    val response: LiveData<SearchResult> = _response
    private val country = MutableLiveData<String>()
    private val entity = MutableLiveData<String>()

    fun getCountry(): MutableLiveData<String> {
        return country
    }

    fun setCountry(country: String) {
        this.country.postValue(country)
    }

    fun getEntity(): MutableLiveData<String> {
        return entity
    }

    fun setEntity(entity: String) {
        this.entity.postValue(entity)
    }

    fun doSearch(request: SearchRequest) {
        viewModelScope.launch {
            try {
                val result = SearchRepository.iTunesApi.doSearch(
                    request.term,
                    request.country,
                    request.media,
                    request.entity,
                    request.offset,
                    request.limit
                )
                _response.value = result
            } catch (e: Exception) {
                Log.e(Constants.LOG_TAG, "Do Search error: " + e.message)
            }
        }
    }

    fun getSelectedEntity (selectedEntityId: Int): String? {
        return when (selectedEntityId) {
            R.id.chip_song -> MediaEntities.SONG
            R.id.chip_artist -> MediaEntities.MUSIC_ARTIST
            R.id.chip_album -> MediaEntities.ALBUM
            R.id.chip_mv -> MediaEntities.MUSIC_VIDEO
            else -> null
        }
    }
}