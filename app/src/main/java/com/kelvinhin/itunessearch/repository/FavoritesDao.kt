package com.kelvinhin.itunessearch.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kelvinhin.itunessearch.data.Results

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM results")
    fun getAll(): LiveData<List<Results>>

    @Query("SELECT EXISTS(SELECT * FROM results WHERE collectionId = :collectionId)")
    suspend fun isInFavoriteList(collectionId: Int): Boolean

    @Insert
    suspend fun insert(item: Results)

    @Update
    suspend fun update(item: Results)

    @Delete
    suspend fun delete(item: Results)
}