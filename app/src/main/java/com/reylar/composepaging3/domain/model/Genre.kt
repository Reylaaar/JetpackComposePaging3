package com.reylar.composepaging3.domain.model

data class Genre(
    val id: Int,
    val name: String,
)

data class Genres(
    val genres: List<Genre>
    )
