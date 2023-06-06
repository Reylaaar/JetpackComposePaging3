package com.reylar.composepaging3.domain.repo

import androidx.paging.PagingData
import com.reylar.composepaging3.common.Resource
import com.reylar.composepaging3.data.db.MovieEntity

import com.reylar.composepaging3.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviePaginatedRepository {

     fun nowPlayingPaging() : Resource<Flow<PagingData<Movie>>>

     fun nowPlayingDaoPaging() : Flow<PagingData<MovieEntity>>
}