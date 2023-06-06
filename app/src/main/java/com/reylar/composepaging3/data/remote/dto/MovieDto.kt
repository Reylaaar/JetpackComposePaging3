package com.reylar.composepaging3.data.remote.dto



data class MovieDto(
    val id: Int,
    val adult: Boolean,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val page : Int,
)