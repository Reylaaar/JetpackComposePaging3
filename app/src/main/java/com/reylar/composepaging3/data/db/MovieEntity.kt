package com.reylar.composepaging3.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "title")
    @Json(name = "title")
    val title: String,
    @ColumnInfo(name = "page")
    var page: Int?,
)