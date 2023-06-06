package com.reylar.composepaging3.domain.model

import com.reylar.composepaging3.data.db.MovieEntity

data class MovieResponseDto(
    val page: Int,
    val results: List<MovieEntity>,
    val total_pages: Int,
    val total_results: Int,
)

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int,
)

