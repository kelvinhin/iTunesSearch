package com.kelvinhin.itunessearch.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kelvinhin.itunessearch.data.Results
import com.kelvinhin.itunessearch.repository.FavoritesDao

class FavoritesViewModel : ViewModel() {
    private lateinit var favoritesDao: FavoritesDao

    private val _favoritesData = MutableLiveData<List<Results>>()
    val favoritesData: LiveData<List<Results>> = _favoritesData
}