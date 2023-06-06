package com.reylar.composepaging3.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.reylar.composepaging3.BuildConfig

@Composable
fun MovieScreenPaging(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val moviesItem = homeViewModel.movies.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            count = moviesItem.itemCount,
            key = moviesItem.itemKey(),
            contentType = moviesItem.itemContentType()
        ) { index ->
            val movie = moviesItem[index]

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                var isImageLoading by remember { mutableStateOf(false) }

                val painter = rememberAsyncImagePainter(
                    model = BuildConfig.POSTER_BASE_URL + movie?.poster_path,
                )

                isImageLoading = when (painter.state) {
                    is AsyncImagePainter.State.Loading -> true
                    else -> false
                }

                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 3.dp)
                            .height(115.dp)
                            .width(77.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        painter = painter,
                        contentDescription = "Poster Image",
                        contentScale = ContentScale.FillBounds,
                    )

                    if (isImageLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 3.dp),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${movie?.title}"
                )
            }
            Divider()
        }
    }
}