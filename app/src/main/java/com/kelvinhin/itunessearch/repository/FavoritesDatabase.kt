package com.kelvinhin.itunessearch.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kelvinhin.itunessearch.constants.Constants
import com.kelvinhin.itunessearch.data.Results

@Database(entities = [Results::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var dbInstance: FavoritesDatabase? = null

        fun getInstance(context: Context): FavoritesDatabase {
            return dbInstance ?: synchronized(this) {
                dbInstance ?: buildDatabase(context).also { dbInstance = it }
            }
        }

        private fun buildDatabase(context: Context): FavoritesDatabase {
            return Room.databaseBuilder(context, FavoritesDatabase::class.java, Constants.DB_NAME).build()
        }
    }

    abstract fun favoritesDao(): FavoritesDao
}