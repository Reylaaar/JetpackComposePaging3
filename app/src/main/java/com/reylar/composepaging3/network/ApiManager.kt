package com.reylar.composepaging3.network

import com.reylar.composepaging3.data.remote.dto.MovieDto
import com.reylar.composepaging3.domain.model.MovieResponse
import com.reylar.composepaging3.domain.model.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiManager {

    //Paging Source
    @GET("movie/popular")
    suspend fun getMoviesByGenreId(
        @Query("page") page: Int,
    ): Response<MovieResponse>

    //Room Mediator
    @GET("movie/popular")
    suspend fun popularMovieListRoomMediator(
        @Query("page") page: Int,
        @Query("language") language: String
    ):  Response<MovieResponseDto>

}