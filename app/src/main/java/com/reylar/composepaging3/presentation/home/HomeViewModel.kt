package com.reylar.composepaging3.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.reylar.composepaging3.data.db.MovieEntity
import com.reylar.composepaging3.data.db.local.MovieDatabase
import com.reylar.composepaging3.data.remote.mediator.MoviesRemoteMediator
import com.reylar.composepaging3.domain.repo.MoviePaginatedRepository
import com.reylar.composepaging3.network.ApiManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val PAGE_SIZE = 20

@HiltViewModel
class HomeViewModel @Inject constructor(
    paginatedRepository: MoviePaginatedRepository,
    private val moviesApiService: ApiManager,
    private val moviesDatabase: MovieDatabase,
) : ViewModel() {

    val movies = paginatedRepository.nowPlayingPaging().data!!.cachedIn(viewModelScope)

    val roomMovies = paginatedRepository.nowPlayingDaoPaging().cachedIn(viewModelScope)
}