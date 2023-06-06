package com.reylar.composepaging3.data.mapper

import com.reylar.composepaging3.data.db.MovieEntity
import com.reylar.composepaging3.data.remote.dto.MovieDto
import com.reylar.composepaging3.domain.model.Movie

//You can manipulate the data using Dto -> Entity, Entity -> Object

fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        overview = overview,
        popularity = popularity,
        posterPath = poster_path,
        title = title,
        page = page
    )
}
