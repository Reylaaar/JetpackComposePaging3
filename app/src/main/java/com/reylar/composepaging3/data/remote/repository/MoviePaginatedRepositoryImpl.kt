package com.reylar.composepaging3.data.remote.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.reylar.composepaging3.common.Resource
import com.reylar.composepaging3.data.db.MovieEntity
import com.reylar.composepaging3.data.db.local.MovieDatabase
import com.reylar.composepaging3.data.remote.mediator.MoviesRemoteMediator
import com.reylar.composepaging3.data.remote.pagesource.NowPlayingPagingSource
import com.reylar.composepaging3.domain.model.Movie
import com.reylar.composepaging3.domain.repo.MoviePaginatedRepository
import com.reylar.composepaging3.network.ApiManager
import com.reylar.composepaging3.presentation.home.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class MoviePaginatedRepositoryImpl(
    private val apiManager: ApiManager,
    private val movieDb: MovieDatabase,
) : MoviePaginatedRepository {
    override fun nowPlayingPaging(): Resource<Flow<PagingData<Movie>>> {
        return Resource.Success(
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    NowPlayingPagingSource(
                        apiManager = apiManager,
                    )
                }
            ).flow
        )
    }

    override fun nowPlayingDaoPaging(): Flow<PagingData<MovieEntity>> {
        return  Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = 10,
                initialLoadSize = PAGE_SIZE,
            ),
            pagingSourceFactory = {
                movieDb.getMoviesDao().getMoviesEntity()
            },
            remoteMediator = MoviesRemoteMediator(
                moviesApiService = apiManager,
                moviesDatabase = movieDb,
            )
        ).flow
    }
}
