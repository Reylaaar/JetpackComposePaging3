package com.reylar.composepaging3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.reylar.composepaging3.common.localNavController
import com.reylar.composepaging3.common.localNavHomeViewModel
import com.reylar.composepaging3.presentation.home.HomeViewModel
import com.reylar.composepaging3.presentation.home.MovieScreenDao
import com.reylar.composepaging3.presentation.home.MovieScreenPaging
import com.reylar.composepaging3.ui.theme.ComposePaging3Theme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val mainNavController = rememberAnimatedNavController()
            val homeViewModel: HomeViewModel = hiltViewModel()

            ComposePaging3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompositionLocalProvider(
                        localNavController provides mainNavController,
                        localNavHomeViewModel provides homeViewModel
                    ) {
                        NavHost(
                            navController = mainNavController,
                            startDestination = "homeScreen"
                        ) {
                            composable(
                                route = "homeScreen"
                            ) {
                                //Room Mediator
                                MovieScreenDao()

                                //TODO  Paging Source
                                //MovieScreenPaging()
                            }
                        }
                    }
                }
            }
        }
    }
}
