package com.kelvinhin.itunessearch.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvinhin.itunessearch.repository.FavoritesDao
import com.kelvinhin.itunessearch.repository.FavoritesDatabase
import kotlinx.coroutines.launch

class SongDetailViewModel : ViewModel() {
    private lateinit var favoritesDao: FavoritesDao

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun checkIsFavorite(dbInstance: FavoritesDatabase, collectionId: Int) {
        viewModelScope.launch {
            favoritesDao = dbInstance.favoritesDao()
            _isFavorite.postValue(favoritesDao.isInFavoriteList(collectionId))
        }
    }

}