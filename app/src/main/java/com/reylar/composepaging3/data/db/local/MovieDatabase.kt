package com.reylar.composepaging3.data.db.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reylar.composepaging3.data.db.MovieEntity
import com.reylar.composepaging3.data.db.RemoteKeys

@Database(entities = [MovieEntity::class,RemoteKeys::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MovieDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}