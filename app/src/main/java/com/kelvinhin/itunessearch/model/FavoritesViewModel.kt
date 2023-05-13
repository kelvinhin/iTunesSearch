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

class FavoritesViewModel : ViewModel() {
    private lateinit var favoritesDao: FavoritesDao

    private val _favoritesData = MutableLiveData<List<Results>>()
    val favoritesData: LiveData<List<Results>> = _favoritesData

    fun getFavoritesList(context: Context) {
        viewModelScope.launch {
            val dbInstance = FavoritesDatabase.getInstance(context)
            favoritesDao = dbInstance.favoritesDao()
            _favoritesData.postValue(favoritesDao.getAll())
        }
    }
}