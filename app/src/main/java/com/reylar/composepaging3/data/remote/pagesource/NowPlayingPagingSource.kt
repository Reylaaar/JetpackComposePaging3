package com.reylar.composepaging3.data.remote.pagesource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.reylar.composepaging3.domain.model.Movie
import com.reylar.composepaging3.network.ApiManager

class NowPlayingPagingSource(
    private val apiManager: ApiManager,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
       val nextPageNumber = params.key ?: 1
        val offset = if(params.key != null) ((nextPageNumber - 1) * 20) + 1 else 1

        return try {
            val response = apiManager.getMoviesByGenreId(page = offset).body()

            val nextKey = if(response?.results?.isEmpty() == true){
                null
            }else{
                nextPageNumber + (params.loadSize / 20)
            }

            LoadResult.Page(
                data = response?.results ?: emptyList(),
                prevKey = null,
                nextKey = nextKey
            )

        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }
}