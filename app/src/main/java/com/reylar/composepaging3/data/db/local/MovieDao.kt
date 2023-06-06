package com.reylar.composepaging3.data.db.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reylar.composepaging3.data.db.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("Select * From movies Order By page")
    fun getMoviesEntity() : PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun clearAllMovies()
}