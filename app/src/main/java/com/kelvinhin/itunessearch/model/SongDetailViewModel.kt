package com.kelvinhin.itunessearch.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvinhin.itunessearch.data.Results
import com.kelvinhin.itunessearch.repository.FavoritesDao
import com.kelvinhin.itunessearch.repository.FavoritesDatabase
import kotlinx.coroutines.launch

class SongDetailViewModel : ViewModel() {
    private lateinit var favoritesDao: FavoritesDao

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun checkIsFavorite(context: Context, collectionId: Int) {
        viewModelScope.launch {
            val dbInstance = FavoritesDatabase.getInstance(context)
            favoritesDao = dbInstance.favoritesDao()
            _isFavorite.postValue(favoritesDao.isInFavoriteList(collectionId))
        }
    }

    fun clickFavoriteState(item: Results) {
        viewModelScope.launch {
            if (isFavorite.value == false) {
                favoritesDao.insert(item)
                _isFavorite.postValue(true)
            } else {
                favoritesDao.delete(item)
                _isFavorite.postValue(false)
            }
        }
    }

}